package com.tobeagile.training.ebaby.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.tobeagile.training.ebaby.domain.User;

public class TestBidder extends BaseTestClass{

	@Test
	public void testPlacingABid() {
		bidAmount = 70.00; 
		userService.logIn(buyer);
		
		auction = createTestAuction("Seller1", auctionStartDateTime, auctionEndDateTime);
		auctionService.changeAuctionState(auction);
		auctionService.placeBid(bidAmount, auction, buyer);
		auctionService.changeAuctionState(auction);
		assertEquals(bidAmount, auction.getPrice());
	}
	
	@Test
	public void testPlacingBidAsSeller() {
		bidAmount = 70.00;
		
		auction = createTestAuction("Seller1", auctionStartDateTime, auctionEndDateTime);
		
		auctionService.placeBid(bidAmount, auction, auction.getSeller());
		assertNotEquals(bidAmount, auction.getPrice());
	}

	@Test
	public void testPlacingLowerOrEqualBid() {
		bidAmount = 5.00; 
		userService.logIn(buyer);
		
		auction = createTestAuction("Seller1", auctionStartDateTime, auctionEndDateTime);
				
		auctionService.placeBid(bidAmount, auction, buyer);
		assertNotEquals(bidAmount, auction.getPrice());
	}
	
	@Test
	public void testPlacingSameBid() {
		User originalBidder = createUser("bob", "johnson", "bob@gmail.com", "password", "Buyer1");
		User secondBidder = createUser("bob", "johnson", "bob@gmail.com", "password", "Buyer2");
		Double bidAmount = 50.00; 
		Double bidAmount2 = 50.00;
		userService.logIn(originalBidder);
		userService.logIn(secondBidder);
		
		auction = createTestAuction("Seller1", auctionStartDateTime, auctionEndDateTime);
		
		auctionService.changeAuctionState(auction);
		auctionService.placeBid(bidAmount, auction, originalBidder);

		assertEquals(bidAmount, auction.getPrice());
		
		auctionService.placeBid(bidAmount2, auction, secondBidder);
		
		assertEquals(originalBidder.getUserName(), auction.getHighBidder().getUserName());	
	}
}