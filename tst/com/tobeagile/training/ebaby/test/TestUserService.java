package com.tobeagile.training.ebaby.test;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tobeagile.training.ebaby.domain.User;
import com.tobeagile.training.ebaby.services.UserService;


public class TestUserService {

	private UserService userService = new UserService();
	private String firstName = "John";
	private String lastName = "Doe";
	private String email = "johnDoe@me.com";
	private String password = "myPass";
	private String userName = "User1";

	@Test
	public void testUserRegisterIsNotLoggedIn() {
		
		User user1 = new User(firstName, lastName, email,userName,password);
		userService.addUser(user1);
		assertEquals(false, userService.getUser(userName).isLoggedIn());
	}
	

	@Test
	public void testUserRegisterLoggingOut() {

		User user1 = new User(firstName, lastName, email,userName,password);
		userService.addUser(user1);
		userService.logOut(user1);
		assertEquals(false, userService.getUser(userName).isLoggedIn());
	}
	
	@Test
	public void testUserRegistration() {

		userService.register(firstName, lastName, email, userName, password);		
		assertEquals(userName, userService.getUser(userName).getUserName());
	}
	
		
	@Test
	public void testUserRegisterIsLoggedIn() {
		String firstNameUser1 = "John";
		String lastNameUser1 = "Doe";
		String emailUser1 = "johnDoe@me.com";
		String passwordUser1 = "myPass";
		String userNameUser1 = "User1";
		
		String firstNameUser2 = "Johnny";
		String lastNameUser2 = "Does";
		String emailUser2 = "johnDoes@me.com";
		String passwordUser2 = "myPasses";
		String userNameUser2 = "User2";
		
		User user1 = new User(firstNameUser1, lastNameUser1, emailUser1, userNameUser1, passwordUser1);
		User user2 = new User(firstNameUser2, lastNameUser2, emailUser2, userNameUser2, passwordUser2);
		
		userService.addUser(user1);
		userService.addUser(user2);
		userService.logIn(user2);
		assertEquals(false, userService.getUser(userNameUser1).isLoggedIn());
		assertEquals(true, userService.getUser(userNameUser2).isLoggedIn());
	}
	
	@Test
	public void testDuplicateUser()
	{
		String firstNameUser1 = "John";
		String lastNameUser1 = "Doe";
		String emailUser1 = "johnDoe@me.com";
		String passwordUser1 = "myPass";
		String userNameUser1 = "User1";
		
		String firstNameUser2 = "Johnny";
		String lastNameUser2 = "Does";
		String emailUser2 = "johnDoes@me.com";
		String passwordUser2 = "myPasses";
		String userNameUser2 = "User1";
		
		User user1 = new User(firstNameUser1, lastNameUser1, emailUser1, userNameUser1, passwordUser1);
		User user2 = new User(firstNameUser2, lastNameUser2, emailUser2, userNameUser2, passwordUser2);
		userService.addUser(user1);
		assertEquals(false, userService.addUser(user2));
	}
	
	@Test
	public void testCheckIfAuthenticatedSeller()
	{
		
		User user = userService.register(firstName, lastName, email, userName, password);	
		userService.setAsSeller(user);
		assertEquals(true, userService.isSeller(user));
	}
}
