package com.leandro.simpleEverythingAPI.services;

import java.util.List;

import com.leandro.simpleEverythingAPI.models.Record;

public interface IRecordService {
	public List<Record> findAll();
	public Record findById(String id);
	public Record createRecord(Record record);
	public Object updateRecord(Record record);
	public Record deleteRecord(String id);
}
