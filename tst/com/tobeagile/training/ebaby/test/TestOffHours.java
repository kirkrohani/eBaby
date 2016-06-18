package com.tobeagile.training.ebaby.test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

import com.tobeagile.training.ebaby.domain.Auction;
import com.tobeagile.training.ebaby.domain.Auction.AuctionCategory;
import com.tobeagile.training.ebaby.domain.User;

public class TestOffHours extends BaseTestClass {

	@Test
	public void testAuctionClosedOffHours() {
		User seller = createUser("bob", "johnson", "bob@gmail.com", "password", "Seller1");
		bidAmount = 10.01;
		Double price = 10.00;
		
		Auction mockAuction = new MockAuction(seller,description,price,auctionStartDateTime,auctionEndDateTime);
		
		userService.logIn(seller);
		userService.setAsSeller(seller);
		
		auctionService.changeAuctionState(mockAuction);	 //Start the Auction
		
		userService.logIn(buyer);
		auctionService.placeBid(bidAmount, mockAuction, buyer);
		
		auctionService.changeAuctionState(mockAuction);  //Close the auction
		auctionService.onClose(mockAuction); //Send notifications and log files

		assertEquals(true, logger.findMessage("offHoursLog.log", mockAuction.getAuctionId() + CLOSED_AFTER_HOURS));
	}
	
	@Test
	public void testAuctionClosedBusinessHours() {
		User seller = createUser("bob", "johnson", "bob@gmail.com", "password", "Seller1");
		bidAmount = 10.01;
		Double price = 10.00;
		
		auctionStartDateTime = LocalDateTime.now().plusSeconds(30);
		auctionEndDateTime = LocalDateTime.now().plusDays(2);
		
		Auction mockAuction = new MockAuction(seller, description,price,auctionStartDateTime,auctionEndDateTime);
		mockAuction.setClosedOffHours(false);  //Setting the auction to close DURING business hours rather than off hours
		mockAuction.setAuctionCategory(AuctionCategory.CAR);
		
		userService.logIn(seller);
		userService.setAsSeller(seller);
		
		auctionService.changeAuctionState(mockAuction);	 //Start the Auction
		
		userService.logIn(buyer);
		auctionService.placeBid(bidAmount, mockAuction, buyer);
		
		auctionService.changeAuctionState(mockAuction);  //Close the auction
		auctionService.onClose(mockAuction); //Send notifications and log files

		assertEquals(false, logger.findMessage("offHoursLog.log", mockAuction.getAuctionId() + CLOSED_AFTER_HOURS));
		assertEquals(true, logger.findMessage("auctionLog.log", mockAuction.getAuctionId() + isCarAuction));
	}
}
