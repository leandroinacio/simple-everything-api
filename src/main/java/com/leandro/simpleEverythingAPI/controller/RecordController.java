package com.leandro.simpleEverythingAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.leandro.simpleEverythingAPI.models.Record;
import com.leandro.simpleEverythingAPI.repositories.RecordRepo;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/record")
public class RecordController {

	@Autowired
	private RecordRepo recordRepo;

	@PostMapping("/fetchAll")
	@PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody Iterable<Record> fetchAll() {
		return recordRepo.findAll();
	}

	/**
	 * @PostMapping("/fetchByUser") public @ResponseBody Iterable<Record>
	 * fetchByOwner(Long userid) { return recordRepo.findOne(userid); }
	 **/

	/**
	 * @PostMapping("/fetchByOwnerAndType") public @ResponseBody
	 * Iterable<Record> fetchByOwner(Long ownerId, Integer recordType) { return
	 * recordRepo.findByOwnerAndType(ownerId, recordType); }
	 **/

	@PostMapping("/insert")
	@PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody Record insert(Record record) {
		return recordRepo.save(record);
	}

//	@PostMapping("/update")
//	@PreAuthorize("hasRole('ADMIN')")
//	public ResponseEntity<?> update(Record record) {
//		Record oldOne = recordRepo.findOne(record.getId());
//		if (oldOne != null) {
//			recordRepo.save(record);
//			return ResponseEntity.ok().build();
//		}
//		return ResponseEntity.notFound().build();
//	}
//
//	@PostMapping("/record/delete")
//	public ResponseEntity<?> delete(Long id) {
//		recordRepo.delete(id);
//		return ResponseEntity.ok().build();
//	}

}