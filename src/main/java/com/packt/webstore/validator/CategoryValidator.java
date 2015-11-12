package com.packt.webstore.validator;

import com.packt.webstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class CategoryValidator implements ConstraintValidator<Category, String> {

    @Autowired
    private ProductService productService;

    @Override
    public void initialize(Category category) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {


        if (Category.allowedCategories.contains(value)==true) return true;
        else return false;

    }

}
