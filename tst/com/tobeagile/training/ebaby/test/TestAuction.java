package com.tobeagile.training.ebaby.test;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.tobeagile.training.ebaby.domain.Auction;
import com.tobeagile.training.ebaby.domain.User;
import com.tobeagile.training.ebaby.services.UserService;


public class TestAuction {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAuctionCreation() {
		UserService users = new UserService();
		String firstNameUser1 = "John";
		String lastNameUser1 = "Doe";
		String emailUser1 = "johnDoe@me.com";
		String passwordUser1 = "myPass";
		String userNameUser1 = "User1";
		
		User u = users.register(firstNameUser1, lastNameUser1, emailUser1, userNameUser1, passwordUser1);
		String description = "This vase is nice.";
		Double price = 100.00;
		LocalDateTime auctionStartDateTime = LocalDateTime.now();
		LocalDateTime auctionEndDateTime = LocalDateTime.now().plusDays(2);
		users.setAsSeller(u);
		
		Auction auction = new Auction(u,description,price,auctionStartDateTime,auctionEndDateTime);
		assertEquals(u.getUserName(), auction.getSeller().getUserName());
		assertEquals(description, auction.getDescription());
		assertEquals(price, auction.getPrice());
		assertEquals(auctionStartDateTime, auction.getAuctionStartDateTime());
		assertEquals(auctionEndDateTime, auction.getAuctionEndDateTime());	
		assertEquals(u.getUserName() + auctionStartDateTime, auction.getAuctionId());
	}
}
