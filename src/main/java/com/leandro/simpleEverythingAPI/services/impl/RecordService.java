package com.leandro.simpleEverythingAPI.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandro.simpleEverythingAPI.models.Record;
import com.leandro.simpleEverythingAPI.repositories.IRecordRepo;
import com.leandro.simpleEverythingAPI.services.IRecordService;

@Service
public class RecordService implements IRecordService {

	private static final Logger log = LoggerFactory.getLogger(RecordService.class);
	
	@Autowired
	private IRecordRepo recordRepo;

	@Override
	public List<Record> findAll() {
		return recordRepo.findAll();
	}
	
	@Override
	public Record createRecord(Record record) {
		return recordRepo.insert(record);
	}

	@Override
	public Object updateRecord(Record record) {
		Record toUpd = recordRepo.findById(record.getId());
		if (toUpd != null) {
			toUpd.setId(record.getId());
			return recordRepo.save(toUpd);
		}
		return new Exception("Record not found");
	}

	@Override
	public Record deleteRecord(String id) {
		return recordRepo.deleteById(id);
	}

	@Override
	public Record findById(String id) {
		return recordRepo.findById(id);
	}

//	@Override
//	public ResponseEntity<Record> inactivateRecord(String recordName) {
//		Query query = new Query();
//		query.addCriteria(Criteria.where("recordName").is(recordName));
//	}

}
