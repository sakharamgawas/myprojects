package com.example.twitter.rep;


import com.example.twitter.models.Rules;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface KeyRepository extends MongoRepository<Rules,Integer> {
    
}
