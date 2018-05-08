package com.leandro.simpleEverythingAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.leandro.simpleEverythingAPI.models.Record;
import com.leandro.simpleEverythingAPI.services.IRecordService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/record")
public class RecordController {

	@Autowired
	private IRecordService recordService;

	@PostMapping("/fetchAll")
	@PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody Iterable<Record> fetchAll() {
		return recordService.findAll();
	}

	@PostMapping("/insert")
	@PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody Record insert(Record record) {
		return recordService.createRecord(record);
	}

	@PostMapping("/update")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Record> update(Record record) {
		Record oldOne = recordService.findById(record.getId());
		if (oldOne != null) {
			recordService.updateRecord(record);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/delete")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Record> delete(String id) {
		recordService.deleteRecord(id);
		return ResponseEntity.ok().build();
	}

}