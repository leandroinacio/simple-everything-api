package com.leandro.simpleEverythingAPI.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.leandro.simpleEverythingAPI.models.Record;

public interface IRecordRepo extends MongoRepository<Record, Long> {
    Record findById(String id);
    List<Record> findAll();
    Record deleteById(String id);
	Record insert(Record record);
	Record save(Record record);
	// https://docs.spring.io/spring-data/mongodb/docs/1.2.0.RELEASE/reference/html/mongo.repositories.html
}
