package com.leandro.simpleEverythingAPI.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.leandro.simpleEverythingAPI.models.VerificationToken;

public interface IVerificationTokenRepo extends MongoRepository<VerificationToken, Long> {
    
}
