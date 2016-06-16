package com.tobeagile.training.ebaby.test;
import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.tobeagile.training.ebaby.domain.User;


public class TestUser {
	private final String firstName = "John";
	private final String lastName = "Doe";
	private final String email = "johnDoe@me.com";
	private final String password = "myPass";
	private final String userName = "myUserName";
	private User user = null;
	
	@Before
	public void setUp() throws Exception {
		user = new User(firstName, lastName, email, userName, password);
	}

	@Test
	public void testIsSeller()
	{
		assertEquals(false, user.isSeller());
	}

	@Test
	public void testUserCreationWithAllFields() {
		assertEquals(firstName,  user.getFirstName());
		assertEquals(lastName, user.getLastName());
		assertEquals(email,  user.getEmail());
		assertEquals(password, user.getPassword());
		assertEquals(userName, user.getUserName());
	}

	@Test
	public void testUserLastName() {			
		assertEquals(lastName,  user.getLastName());
	}
	
	@Test
	public void testUserFirstName() {
		assertEquals(firstName,  user.getFirstName());
	}
	
	@Test
	public void testUserEmail() {
		assertEquals(email,  user.getEmail());	
	}
	
	@Test
	public void testUserPassword() {
		assertEquals(password, user.getPassword());
	}

	@Test
	public void testUserUserName() {
		assertEquals(userName, user.getUserName());
	}
}
