package com.tobeagile.training.ebaby.test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

import com.tobeagile.training.ebaby.domain.Auction;
import com.tobeagile.training.ebaby.domain.User;
import com.tobeagile.training.ebaby.services.AuctionLogger;
import com.tobeagile.training.ebaby.services.AuctionService;
import com.tobeagile.training.ebaby.services.UserService;

public class TestAuctionLogger {
	private UserService userService = new UserService();

	private User createUser(String fname, String lname, String email, String password, String uname)
	{
		String firstNameUser1 = fname;
		String lastNameUser1 = lname;
		String emailUser1 = email;
		String passwordUser1 = password;
		String userNameUser1 = uname;
		User user = userService.register(firstNameUser1, lastNameUser1, emailUser1, userNameUser1, passwordUser1);
		return user;
	}
	
	@Test
	public void testAuctionLoggerForNoLogging() {
		User seller = createUser("bob", "johnson", "bob@gmail.com", "password", "Seller1");
		User buyer = createUser("roy", "johnson", "roy@gmail.com", "password", "buyer1");
		Double bidAmount = 10000.00;
		String description = "This vase is nice.";
		Double price = 10.00;
		LocalDateTime auctionStartDateTime = LocalDateTime.now().plusSeconds(30);
		LocalDateTime auctionEndDateTime = LocalDateTime.now().plusDays(2);
		userService.logIn(seller);
		userService.setAsSeller(seller);
		
		AuctionService auctionService = new AuctionService();
		Auction auction = auctionService.createAuction(seller,description,price,auctionStartDateTime,auctionEndDateTime);
		auctionService.changeAuctionState(auction);	
		userService.logIn(buyer);
		auctionService.placeBid(bidAmount, auction, buyer);
		auctionService.changeAuctionState(auction);

		AuctionLogger logger = AuctionLogger.getInstance();
		assertEquals(false, logger.findMessage("auctionLog.log", auction.getAuctionId() + " is a car auction"));
		assertEquals(false, logger.findMessage("auctionLog.log", auction.getAuctionId() + " is an auction for amount over $10000.00"));
	}
	
	@Test
	public void testAuctionLoggerOnAmountsOnCategoryCarAt10000() {
		User seller = createUser("bob", "johnson", "bob@gmail.com", "password", "Seller1");
		User buyer = createUser("roy", "johnson", "roy@gmail.com", "password", "buyer1");
		Double bidAmount = 10000.00;
		String description = "This vase is nice.";
		Double price = 10.00;
		LocalDateTime auctionStartDateTime = LocalDateTime.now().plusSeconds(30);
		LocalDateTime auctionEndDateTime = LocalDateTime.now().plusDays(2);
		userService.logIn(seller);
		userService.setAsSeller(seller);
		
		AuctionService auctionService = new AuctionService();
		Auction auction = auctionService.createAuction(seller,description,price,auctionStartDateTime,auctionEndDateTime);
		auctionService.changeAuctionState(auction);	
		auctionService.setAutionCategory(auction, "CAR");

		userService.logIn(buyer);
		auctionService.placeBid(bidAmount, auction, buyer);
		auctionService.changeAuctionState(auction);

		AuctionLogger logger = AuctionLogger.getInstance();
		assertEquals(true, logger.findMessage("auctionLog.log", auction.getAuctionId() + " is a car auction"));
		assertEquals(false, logger.findMessage("auctionLog.log", auction.getAuctionId() + " is an auction for amount over $10000.00"));
	}
	
	@Test
	public void testAuctionLoggerOnAmountsGreaterThan10000() {
		User seller = createUser("bob", "johnson", "bob@gmail.com", "password", "Seller1");
		User buyer = createUser("roy", "johnson", "roy@gmail.com", "password", "buyer1");
		Double bidAmount = 10000.01;
		String description = "This vase is nice.";
		Double price = 10.00;
		LocalDateTime auctionStartDateTime = LocalDateTime.now().plusSeconds(30);
		LocalDateTime auctionEndDateTime = LocalDateTime.now().plusDays(2);
		userService.logIn(seller);
		userService.setAsSeller(seller);
		
		AuctionService auctionService = new AuctionService();
		Auction auction = auctionService.createAuction(seller,description,price,auctionStartDateTime,auctionEndDateTime);
		auctionService.changeAuctionState(auction);	
		userService.logIn(buyer);
		auctionService.placeBid(bidAmount, auction, buyer);
		auctionService.changeAuctionState(auction);

		AuctionLogger logger = AuctionLogger.getInstance();
		assertEquals(true, logger.findMessage("auctionLog.log", auction.getAuctionId() + " is an auction for amount over $10000.00"));
		assertEquals(false, logger.findMessage("auctionLog.log", auction.getAuctionId() + " is a car auction"));
		}
	
	@Test
	public void testAuctionLoggerOnCarsGreaterThan10000() {
		User seller = createUser("bob", "johnson", "bob@gmail.com", "password", "Seller1");
		User buyer = createUser("roy", "johnson", "roy@gmail.com", "password", "buyer1");
		Double bidAmount = 10000.01;
		String description = "This vase is nice.";
		Double price = 10.00;
		LocalDateTime auctionStartDateTime = LocalDateTime.now().plusSeconds(30);
		LocalDateTime auctionEndDateTime = LocalDateTime.now().plusDays(2);
		userService.logIn(seller);
		userService.setAsSeller(seller);
	
		
		AuctionService auctionService = new AuctionService();
		Auction auction = auctionService.createAuction(seller,description,price,auctionStartDateTime,auctionEndDateTime);
		auctionService.setAutionCategory(auction, "CAR");
		auctionService.changeAuctionState(auction);	
		userService.logIn(buyer);
		auctionService.placeBid(bidAmount, auction, buyer);
		auctionService.changeAuctionState(auction);

		AuctionLogger logger = AuctionLogger.getInstance();
		assertEquals(true, logger.findMessage("auctionLog.log", auction.getAuctionId() + " is a car auction"));
		assertEquals(true, logger.findMessage("auctionLog.log", auction.getAuctionId() + " is an auction for amount over $10000.00"));
		}
}
