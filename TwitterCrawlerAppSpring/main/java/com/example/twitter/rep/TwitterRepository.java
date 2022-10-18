package com.example.twitter.rep;

import com.example.twitter.models.Tweets;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TwitterRepository extends MongoRepository<Tweets,Integer>{
    
}
