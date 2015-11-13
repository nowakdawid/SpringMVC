package com.packt.webstore.controller;

import com.packt.webstore.domain.Product;
import com.packt.webstore.exception.NoProductsFoundUnderCategoryException;
import com.packt.webstore.exception.ProductNotFoundException;
import com.packt.webstore.service.ProductService;
import com.packt.webstore.validator.ProductValidator;
import com.packt.webstore.validator.UnitsInStockValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.util.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductValidator productValidator;

    @RequestMapping
    public String list(Model model) {

        model.addAttribute("products", productService.getAllProducts());
        return "products";

    }

    @RequestMapping("/all")
    public String allProducts(Model model) {

        model.addAttribute("products", productService.getAllProducts());
        return "products";

    }

    @RequestMapping("/{category}")
    public String getProductsByCategory(Model model, @PathVariable("category") String productCategory) {

        List<Product> products = productService.getProductsByCategory(productCategory);

        if (products == null || products.isEmpty()) {

            throw new NoProductsFoundUnderCategoryException();

        }

        model.addAttribute("products", products);
        return "products";

    }

    @RequestMapping("/filter/{ByCriteria}")
    public String getProductsByFilter(@MatrixVariable(pathVar = "ByCriteria") Map<String, List<String>> filterParams, Model model) {

        model.addAttribute("products", productService.getProductsByFilter(filterParams));
        return "products";

    }

    @RequestMapping("/product")
    public String getProductById(@RequestParam("id") String productId, Model model) {

        model.addAttribute("product", productService.getProductById(productId));
        return "product";

    }

    @RequestMapping("/{category}/{price}")
    public String filterProducts(Model model, @PathVariable("category") String productCategory, @MatrixVariable(pathVar = "price") Map<String, List<String>> price, @RequestParam("manufacturer") String productManufacturer) {

        Set<Product> filteredProducts = new HashSet<Product>();
        filteredProducts.addAll(productService.getProductsByCategory(productCategory));
        filteredProducts.addAll(productService.getProductsByPrice(price));
        filteredProducts.addAll(productService.getProductsByManufacturer(productManufacturer));

        model.addAttribute("products", filteredProducts);
        return "products";

    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAddNewProductForm(Model model) {

        Product newProduct = new Product();
        model.addAttribute("newProduct", newProduct);
        return "addProduct";

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddNewProductForm(@ModelAttribute("newProduct") @Valid Product newProduct, BindingResult result, HttpServletRequest request) {

        if(result.hasErrors()) {
            return "addProduct";
        }

        String[] suppressedFields = result.getSuppressedFields();

        if (suppressedFields.length > 0) {

            throw new RuntimeException("Attempting to bind disallowed fields: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));

        }

        MultipartFile productImage = newProduct.getProductImage();
        MultipartFile productManual = newProduct.getProductManual();

        String rootDirectory = request.getSession().getServletContext().getRealPath("/");

        if (productImage != null && !productImage.isEmpty()) {

            try {
                productImage.transferTo(new File(rootDirectory + "resources\\images\\" + newProduct.getProductId() + ".png"));
            } catch (Exception e) {
                throw new RuntimeException("Product image saving failed", e);
            }

        }

        if (productManual != null && !productManual.isEmpty()) {

            try {
                productManual.transferTo(new File(rootDirectory + "resources\\manuals\\" + newProduct.getProductId() + ".pdf"));
            } catch (Exception e) {
                throw new RuntimeException("Product manual saving failed", e);
            }

        }

        productService.addProduct(newProduct);
        return "redirect:/products";

    }

    @InitBinder
    public void initialiseBinder(WebDataBinder binder) {

        binder.setValidator(productValidator);

        binder.setDisallowedFields("unitsInOrder", "discontinued");

        binder.setAllowedFields("productId","name","unitPrice","description","manufacturer","category","unitsInStock","condition","productImage","productManual","language");

    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ModelAndView handleError(HttpServletRequest req, ProductNotFoundException exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("invalidProductId", exception.getProductId());
        mav.addObject("exception", exception);
        mav.addObject("url", req.getRequestURL()+"?"+req.getQueryString());
        mav.setViewName("productNotFound");
        return mav;
    }

    @RequestMapping("/invalidPromoCode")
    public String invalidPromoCode() {
        return "invalidPromoCode";
    }

}