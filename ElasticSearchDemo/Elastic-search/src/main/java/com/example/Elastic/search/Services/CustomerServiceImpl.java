package com.example.Elastic.search.Services;

import com.example.Elastic.search.Domain.Customer;
import com.example.Elastic.search.Repositery.ElasticRepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private ElasticRepositery elasticRepositery;
    @Override
    public Customer addcustomer(Customer customer) {
        return elasticRepositery.save(customer);
    }

    @Override
    public Iterable<Customer> findall() {
        return elasticRepositery.findAll();
    }

    @Override
    public boolean deletebyid(String email) {
        Customer c1 = elasticRepositery.findById(email).get();
        if(c1!=null)
        {
            elasticRepositery.deleteById(email);
            return true;
        }
        return false;
    }
}
