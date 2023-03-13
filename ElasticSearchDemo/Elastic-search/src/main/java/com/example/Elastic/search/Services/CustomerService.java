package com.example.Elastic.search.Services;

import com.example.Elastic.search.Domain.Customer;

public interface CustomerService {

    public Customer addcustomer(Customer customer);

    public Iterable<Customer> findall();

    public boolean deletebyid(String email);
}
