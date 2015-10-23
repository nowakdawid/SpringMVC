package com.packt.webstore.domain.repository.impl;

import com.packt.webstore.domain.Customer;
import com.packt.webstore.domain.repository.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository {

    private List<Customer> listOfCustomers = new ArrayList<Customer>();

    public InMemoryCustomerRepository() {

        Customer kowalski = new Customer(1, "Jan Kowalski", "ul. Truszkowskiego 32B, Kraków", 3);
        Customer malinowski = new Customer(2, "Zbigniew Malinowski", "ul. D±browskiego 34/5, Bydgoszcz", 5);
        Customer majewski = new Customer(3, "Szymon Majewski", "ul. Batorego 57, Warszawa", 1);

        listOfCustomers.add(kowalski);
        listOfCustomers.add(malinowski);
        listOfCustomers.add(majewski);

    }

    @Override
    public List<Customer> getAllCustomers() {
        return listOfCustomers;
    }

}
