package com.leandro.simpleEverythingAPI.security.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.leandro.simpleEverythingAPI.models.User;
import com.leandro.simpleEverythingAPI.repositories.IUserRepo;
import com.leandro.simpleEverythingAPI.security.Authority;
import com.leandro.simpleEverythingAPI.security.AuthorityName;
import com.leandro.simpleEverythingAPI.utils.PasswordUtils;

@RestController
@RequestMapping("protected")
@CrossOrigin(origins = "http://localhost:8081")
public class MethodProtectedRestController {

	@Autowired
	public IUserRepo userRepo;

	/**
	 * This is an example of some different kinds of granular restriction for
	 * endpoints. You can use the built-in SPEL expressions in @PreAuthorize
	 * such as 'hasRole()' to determine if a user has access. Remember that the
	 * hasRole expression assumes a 'ROLE_' prefix on all role names. So 'ADMIN'
	 * here is actually stored as 'ROLE_ADMIN' in database!
	 **/
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/getGreeting")
	public ResponseEntity<?> getProtectedGreeting() {
		return ResponseEntity.ok("Greetings from admin protected method!");
	}

	@PostMapping("/unprotected")
	public @ResponseBody String fetchAll(@RequestBody User user) {
		List<Authority> list = new ArrayList<>();
		Authority aut = new Authority(0L, AuthorityName.ROLE_ADMIN);
		list.add(aut);
		user.setAuthorities(list);
		user.setLastPasswordResetDate(new Date());
		user.setPassword(PasswordUtils.generateBCrypt(user.getPassword()));
		userRepo.save(user);
		return "funcionou";
	}

	@PostMapping("/getUser")
	public @ResponseBody User getUser() {
		List<Authority> list = new ArrayList<>();
		Authority aut = new Authority(0L, AuthorityName.ROLE_ADMIN);
		list.add(aut);
		return new User("", "leandro", "teste", "leandro", "Inacio", "email@mail", true, new Date(), list);
	}

}