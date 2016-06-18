package com.tobeagile.training.ebaby.test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

import com.tobeagile.training.ebaby.domain.Auction;
import com.tobeagile.training.ebaby.domain.User;
import com.tobeagile.training.ebaby.services.AuctionService;
import com.tobeagile.training.ebaby.services.PostOffice;
import com.tobeagile.training.ebaby.services.UserService;

public class TestSendingNotifications {
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
	public void testNotificationsOnNoBidAuction() {
		User seller = createUser("bob", "johnson", "bob@gmail.com", "password", "Seller1");
		String description = "This vase is nice.";
		Double price = 10.00;
		LocalDateTime auctionStartDateTime = LocalDateTime.now().plusSeconds(30);
		LocalDateTime auctionEndDateTime = LocalDateTime.now().plusDays(2);
		userService.logIn(seller);
		userService.setAsSeller(seller);
		
		AuctionService auctionService = new AuctionService();
		Auction auction = auctionService.createAuction(seller,description,price,auctionStartDateTime,auctionEndDateTime);
		auctionService.changeAuctionState(auction); // opens it
		auctionService.changeAuctionState(auction); // closes it
		
		PostOffice usps = PostOffice.getInstance();
		String message = usps.findEmail(seller.getEmail(), "Sorry");
		assertEquals(true,message.contains("Sorry") );
	}
	
	@Test
	public void testNotificationsOnBidAuction() {
		User seller = createUser("bob", "johnson", "bob@gmail.com", "password", "Seller1");
		User buyer = createUser("roy", "johnson", "roy@gmail.com", "password", "buyer1");
		Double bidAmount = 20.00;
		String description = "This vase is nice.";
		Double price = 10.00;
		LocalDateTime auctionStartDateTime = LocalDateTime.now().plusSeconds(30);
		LocalDateTime auctionEndDateTime = LocalDateTime.now().plusDays(2);
		userService.logIn(seller);
		userService.setAsSeller(seller);
		
		PostOffice usps = PostOffice.getInstance();
		usps.clear();
		AuctionService auctionService = new AuctionService();
		Auction auction = auctionService.createAuction(seller,description,price,auctionStartDateTime,auctionEndDateTime);
		auctionService.changeAuctionState(auction); // opens it		
		userService.logIn(buyer);
		auctionService.placeBid(bidAmount, auction, buyer);
		auctionService.setAuctionCategory(auction, "GENERAL");
		auctionService.changeAuctionState(auction); // closes it
		
		String sellerMessage = usps.findEmail(seller.getEmail(), "sold");
		String buyerMessage = usps.findEmail(buyer.getEmail(), "Congratulations");
		assertEquals(true,sellerMessage.contains("sold") );
		assertEquals(true,buyerMessage.contains("Congratulations") );
	}
	
	@Test
	public void testNotificationsOnBidAuctionAmountsOnCategoryCar() {
		User seller = createUser("bob", "johnson", "bob@gmail.com", "password", "Seller1");
		User buyer = createUser("roy", "johnson", "roy@gmail.com", "password", "buyer1");
		Double bidAmount = 20.00;
		String description = "This vase is nice.";
		Double price = 10.00;
		LocalDateTime auctionStartDateTime = LocalDateTime.now().plusSeconds(30);
		LocalDateTime auctionEndDateTime = LocalDateTime.now().plusDays(2);
		userService.logIn(seller);
		userService.setAsSeller(seller);
		
		PostOffice usps = PostOffice.getInstance();
		usps.clear();
		
		AuctionService auctionService = new AuctionService();
		Auction auction = auctionService.createAuction(seller,description,price,auctionStartDateTime,auctionEndDateTime);
		auctionService.changeAuctionState(auction);	
		userService.logIn(buyer);
		auctionService.placeBid(bidAmount, auction, buyer);
		auctionService.setAuctionCategory(auction, "CAR");
		auctionService.changeAuctionState(auction);
		
		System.out.println(auction);
		
		String sellerMessage = usps.findEmail(seller.getEmail(), "sold");
		String buyerMessage = usps.findEmail(buyer.getEmail(), "Congratulations");

		assertEquals(true,sellerMessage.contains("19.6"));
		assertEquals(true,buyerMessage.contains("1020.0"));
	}
	
	
	@Test
	public void testNotificationsOnBidAuctionAmountsOnCategoryCarAt50000() {
		User seller = createUser("bob", "johnson", "bob@gmail.com", "password", "Seller1");
		User buyer = createUser("roy", "johnson", "roy@gmail.com", "password", "buyer1");
		Double bidAmount = 50000.00;
		String description = "This vase is nice.";
		Double price = 10.00;
		LocalDateTime auctionStartDateTime = LocalDateTime.now().plusSeconds(30);
		LocalDateTime auctionEndDateTime = LocalDateTime.now().plusDays(2);
		userService.logIn(seller);
		userService.setAsSeller(seller);
		
		PostOffice usps = PostOffice.getInstance();
		usps.clear();
		
		AuctionService auctionService = new AuctionService();
		Auction auction = auctionService.createAuction(seller,description,price,auctionStartDateTime,auctionEndDateTime);
		auctionService.changeAuctionState(auction);	
		userService.logIn(buyer);
		auctionService.placeBid(bidAmount, auction, buyer);
		auctionService.setAuctionCategory(auction, "CAR");
		auctionService.changeAuctionState(auction);

		String sellerMessage = usps.findEmail(seller.getEmail(), "sold");
		String buyerMessage = usps.findEmail(buyer.getEmail(), "Congratulations");
		assertEquals(true,sellerMessage.contains("49000.0"));
		assertEquals(true,buyerMessage.contains("51000.0"));
	}
	
	@Test
	public void testNotificationsOnBidAuctionAmountsOnCategoryCarLuxuryAt5000001() {
		User seller = createUser("bob", "johnson", "bob@gmail.com", "password", "Seller1");
		User buyer = createUser("roy", "johnson", "roy@gmail.com", "password", "buyer1");
		Double bidAmount = 50000.01;
		String description = "This vase is nice.";
		Double price = 10.00;
		LocalDateTime auctionStartDateTime = LocalDateTime.now().plusSeconds(30);
		LocalDateTime auctionEndDateTime = LocalDateTime.now().plusDays(2);
		userService.logIn(seller);
		userService.setAsSeller(seller);
		PostOffice usps = PostOffice.getInstance();
		usps.clear();
		AuctionService auctionService = new AuctionService();
		Auction auction = auctionService.createAuction(seller,description,price,auctionStartDateTime,auctionEndDateTime);
		auctionService.changeAuctionState(auction);	
		userService.logIn(buyer);
		auctionService.placeBid(bidAmount, auction, buyer);
		auctionService.setAuctionCategory(auction, "CAR");
		auctionService.changeAuctionState(auction);
		
		String sellerMessage = usps.findEmail(seller.getEmail(), "sold");
		String buyerMessage = usps.findEmail(buyer.getEmail(), "Congratulations");
		assertEquals(true,sellerMessage.contains("49000.00"));
		assertEquals(true,buyerMessage.contains("53000.01"));
	}
}
