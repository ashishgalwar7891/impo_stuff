package com.example.Elastic.search.Repositery;

import com.example.Elastic.search.Domain.Customer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElasticRepositery extends ElasticsearchRepository<Customer,String> {
}
