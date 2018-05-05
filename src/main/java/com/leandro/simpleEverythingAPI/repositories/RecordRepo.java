package com.leandro.simpleEverythingAPI.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.leandro.simpleEverythingAPI.models.Record;

public interface RecordRepo extends MongoRepository<Record, Long> {

	// https://docs.spring.io/spring-data/mongodb/docs/1.2.0.RELEASE/reference/html/mongo.repositories.html
}
