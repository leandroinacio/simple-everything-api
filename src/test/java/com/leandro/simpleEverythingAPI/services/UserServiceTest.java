package com.leandro.simpleEverythingAPI.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.leandro.simpleEverythingAPI.models.User;
import com.leandro.simpleEverythingAPI.repositories.IUserRepo;
import com.leandro.simpleEverythingAPI.services.impl.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

	@MockBean
	private IUserRepo userRepo;

	@Autowired
	private UserService userService;

//	public Object updateUser(User user);
//	public String deleteUser(String userName);
	
	
	@Before
	public void setUp() throws Exception {
		ArrayList<User> result = new ArrayList<User>();
		result.add(new User());
		BDDMockito.given(this.userRepo.findAll()).willReturn(result);
		BDDMockito.given(this.userRepo.findByUsername(Mockito.anyString())).willReturn(new User());
		BDDMockito.given(this.userRepo.insert(Mockito.any(User.class))).willReturn(new User());
		BDDMockito.given(this.userRepo.save(Mockito.any(User.class))).willReturn(new User());
		BDDMockito.given(this.userRepo.deleteByUsername(Mockito.anyString())).willReturn(new User());
	}

	@Test
	public void testFindAll() {
		List<User> users = this.userService.findAll();
		assertEquals(1, users.size());
	}
	
	@Test
	public void testFindByUsername() {
		User user = this.userService.findByUsername("test");
		assertNotNull(user);
	}
	
	@Test
	public void testCreateUser() {
		User user = this.userService.createUser(new User());
		assertNotNull(user);	
	}
	
	@Test
	public void testUpdateUser() {
		Object user = this.userService.updateUser(new User());
		assertNotNull(user);	
	}
	
	@Test
	public void testDeleteUser() {
		User user = this.userService.deleteUser("test");
		assertNotNull(user);	
	}
}