package com.tobeagile.training.ebaby.test;

import java.time.LocalDateTime;

import com.tobeagile.training.ebaby.domain.Auction;
import com.tobeagile.training.ebaby.domain.User;
import com.tobeagile.training.ebaby.services.AuctionLogger;
import com.tobeagile.training.ebaby.services.AuctionService;
import com.tobeagile.training.ebaby.services.UserService;

public class BaseTestClass {
	protected UserService userService = new UserService();
	protected AuctionService auctionService = new AuctionService();
	protected LocalDateTime auctionStartDateTime = null;
	protected LocalDateTime auctionEndDateTime = null;
	protected User buyer = createUser("roy", "johnson", "roy@gmail.com", "password", "buyer1");
	protected String description = "This vase is nice.";
	protected AuctionLogger logger = AuctionLogger.getInstance();
	protected Double bidAmount = 0.00;
	protected String isOverTenThousand = " is an auction for amount over $10000.00";
	protected String isCarAuction = " is a car auction";
	protected Auction auction = null;
	protected final String CLOSED_AFTER_HOURS = " closed after hours";

	protected User createUser(String fname, String lname, String email, String password, String uname)
	{
		String firstNameUser1 = fname;
		String lastNameUser1 = lname;
		String emailUser1 = email;
		String passwordUser1 = password;
		String userNameUser1 = uname;
		User user = userService.register(firstNameUser1, lastNameUser1, emailUser1, userNameUser1, passwordUser1);
		return user;
	}
	
	protected Auction createTestAuction(String sellerId, LocalDateTime startAuctionDateTime, LocalDateTime endAuctionDateTime)
	{
		User seller = createUser("bob", "johnson", "bob@gmail.com", "password", sellerId);
		Double price = 10.00;
		auctionStartDateTime = LocalDateTime.now().plusSeconds(30);
		auctionEndDateTime = LocalDateTime.now().plusDays(2);
		userService.logIn(seller);
		userService.setAsSeller(seller);
				
		return 	auctionService.createAuction(seller,description,price,auctionStartDateTime,auctionEndDateTime);
	}
}
