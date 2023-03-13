package com.pixelTrice.elastic;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class ElasticSearchController {

    @Autowired
    private ElasticSearchQuery elasticSearchQuery;

    //http://localhost:8080/createOrUpdateDocument
    @PostMapping("/createOrUpdateDocument")
    public ResponseEntity<Object> createOrUpdateDocument(@RequestBody Product product) throws IOException {
          String response = elasticSearchQuery.createOrUpdateDocument(product);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    //http://localhost:8080/getDocument/{productId}
    @GetMapping("/getDocument/{productId}")
    public ResponseEntity<Object> getDocumentById(@PathVariable String productId) throws IOException {
       Product product =  elasticSearchQuery.getDocumentById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    //http://localhost:8080/deleteDocument/{productId}
    @DeleteMapping("/deleteDocument/{productId}")
    public ResponseEntity<Object> deleteDocumentById(@PathVariable String productId) throws IOException {
        String response =  elasticSearchQuery.deleteDocumentById(productId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    //http://localhost:8080/searchDocument
    @GetMapping("/searchDocument")
    public ResponseEntity<Object> searchAllDocument() throws IOException {
        List<Product> products = elasticSearchQuery.searchAllDocuments();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
