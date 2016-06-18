package com.tobeagile.training.ebaby.test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

import com.tobeagile.training.ebaby.domain.Auction;
import com.tobeagile.training.ebaby.domain.User;
import com.tobeagile.training.ebaby.services.AuctionService;
import com.tobeagile.training.ebaby.services.UserService;

public class TestBidder {
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
	public void testPlacingABid() {
		User seller = createUser("bob", "johnson", "bob@gmail.com", "password", "Seller1");
		User bidder = createUser("bob", "johnson", "bob@gmail.com", "password", "Buyer1");
		String description = "This vase is nice.";
		Double price = 10.00;
		LocalDateTime auctionStartDateTime = LocalDateTime.now().plusSeconds(30);
		LocalDateTime auctionEndDateTime = LocalDateTime.now().plusDays(2);
		Double bidAmount = 70.00; 
		userService.logIn(seller);
		userService.logIn(bidder);
		userService.setAsSeller(seller);
		
		AuctionService auctionService = new AuctionService();
		Auction auction = auctionService.createAuction(seller,description,price,auctionStartDateTime,auctionEndDateTime);		
		
		auctionService.placeBid(bidAmount, auction, bidder);
		assertEquals(bidAmount, auction.getPrice());
	}
	
	@Test
	public void testPlacingBidAsSeller() {
		User seller = createUser("bob", "johnson", "bob@gmail.com", "password", "Seller1");
		String description = "This vase is nice.";
		Double price = 10.00;
		LocalDateTime auctionStartDateTime = LocalDateTime.now().plusSeconds(30);
		LocalDateTime auctionEndDateTime = LocalDateTime.now().plusDays(2);
		Double bidAmount = 70.00; 
		userService.logIn(seller);
		userService.setAsSeller(seller);
		
		AuctionService auctionService = new AuctionService();
		Auction auction = auctionService.createAuction(seller,description,price,auctionStartDateTime,auctionEndDateTime);
		//System.out.println("auction :"+ auction);
		
		
		auctionService.placeBid(bidAmount, auction, seller);
		assertNotEquals(bidAmount, auction.getPrice());
		
	}

	@Test
	public void testPlacingLowerOrEqualBid() {
		User seller = createUser("bob", "johnson", "bob@gmail.com", "password", "Seller1");
		User bidder = createUser("bob", "johnson", "bob@gmail.com", "password", "Buyer1");
		String description = "This vase is nice.";
		Double price = 10.00;
		LocalDateTime auctionStartDateTime = LocalDateTime.now().plusSeconds(30);
		LocalDateTime auctionEndDateTime = LocalDateTime.now().plusDays(2);
		Double bidAmount = 5.00; 
		userService.logIn(seller);
		userService.logIn(bidder);
		userService.setAsSeller(seller);
		
		AuctionService auctionService = new AuctionService();
		Auction auction = auctionService.createAuction(seller,description,price,auctionStartDateTime,auctionEndDateTime);
		//System.out.println("auction :"+ auction);
				
		auctionService.placeBid(bidAmount, auction, bidder);
		System.out.println("bidAmount: " + bidAmount + "\n current price: " + auction.getPrice());
		assertNotEquals(bidAmount, auction.getPrice());
		
	}
	
	@Test
	public void testPlacingSameBid() {
		User seller = createUser("bob", "johnson", "bob@gmail.com", "password", "Seller1");
		User originalBidder = createUser("bob", "johnson", "bob@gmail.com", "password", "Buyer1");
		User secondBidder = createUser("bob", "johnson", "bob@gmail.com", "password", "Buyer2");
		String description = "This vase is nice.";
		Double price = 10.00;
		LocalDateTime auctionStartDateTime = LocalDateTime.now().plusSeconds(30);
		LocalDateTime auctionEndDateTime = LocalDateTime.now().plusDays(2);
		Double bidAmount = 50.00; 
		Double bidAmount2 = 50.00;
		userService.logIn(seller);
		userService.logIn(originalBidder);
		userService.logIn(secondBidder);
		userService.setAsSeller(seller);
		
		AuctionService auctionService = new AuctionService();
		Auction auction = auctionService.createAuction(seller,description,price,auctionStartDateTime,auctionEndDateTime);
		//System.out.println("auction :"+ auction);
		
		auctionService.placeBid(bidAmount, auction, originalBidder);
		System.out.println("bidAmount1: " + bidAmount + "\n current price1: " + auction.getPrice());
		assertEquals(bidAmount, auction.getPrice());
		
		auctionService.placeBid(bidAmount2, auction, secondBidder);
		System.out.println("bidAmount2: " + bidAmount2 + "\n current price2: " + auction.getPrice());
		System.out.println("auction: " + auction);
		assertEquals(originalBidder.getUserName(), auction.getHighBidder().getUserName());
		
	}
	

}
