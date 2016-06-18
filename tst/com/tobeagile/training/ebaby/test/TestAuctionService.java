package com.tobeagile.training.ebaby.test;
import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

import com.tobeagile.training.ebaby.domain.Auction;
import com.tobeagile.training.ebaby.domain.User;
import com.tobeagile.training.ebaby.services.AuctionService;
import com.tobeagile.training.ebaby.services.UserService;


public class TestAuctionService extends BaseTestClass {

	@Test
	public void testAddAuctionToAuctions(){
		UserService users = new UserService();
		String firstNameUser1 = "John";
		String lastNameUser1 = "Doe";
		String emailUser1 = "johnDoe@me.com";
		String passwordUser1 = "myPass";
		String userNameUser1 = "User1";
		
		auctionStartDateTime = LocalDateTime.now().plusSeconds(30);
		auctionEndDateTime = LocalDateTime.now().plusDays(2);
		
		User user = users.register(firstNameUser1, lastNameUser1, emailUser1, userNameUser1, passwordUser1);
		Double price = 100.00;
	
		users.logIn(user);
		users.setAsSeller(user);
		
		auction = auctionService.createAuction(user,description,price,auctionStartDateTime,auctionEndDateTime);
		assertEquals(user.getUserName() + auctionStartDateTime, auction.getAuctionId());
	}
	
	@Test
	public void testAuctionState() throws Exception
	{	
		auction = createTestAuction("Seller1", auctionStartDateTime, auctionEndDateTime);
		assertEquals(auction.getAuctionState(), Auction.AuctionState.NOT_STARTED);
		auctionService.changeAuctionState(auction);
		assertEquals(auction.getAuctionState(), Auction.AuctionState.OPEN);
		auctionService.changeAuctionState(auction);
		assertEquals(auction.getAuctionState(), Auction.AuctionState.CLOSED);
		auctionService.changeAuctionState(auction);
		assertEquals(auction.getAuctionState(), Auction.AuctionState.CLOSED);
	}
}