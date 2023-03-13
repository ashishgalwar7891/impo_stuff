package com.example.Elastic.search.Controller;

import com.example.Elastic.search.Domain.Customer;
import com.example.Elastic.search.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class ElasticController {

    @Autowired
    private CustomerService customerService;

    //http://localhost:9900/add
    @PostMapping("/add")
    public ResponseEntity<?> adduser(@RequestBody Customer customer)
    {
        return new ResponseEntity<>(customerService.addcustomer(customer), HttpStatus.OK);
    }
    //http://localhost:9900/getall
    @GetMapping("/getall")
    public ResponseEntity<?> getalluser(@RequestBody Customer customer)
    {
        return new ResponseEntity<>(customerService.findall(), HttpStatus.OK);
    }
    //http://localhost:9900/delete/{email}
    @DeleteMapping("/delete/{email}")
    public ResponseEntity<?> getalluser(@PathVariable String email)
    {
        System.out.println(email);
        return new ResponseEntity<>(customerService.deletebyid(email), HttpStatus.OK);
    }
}
