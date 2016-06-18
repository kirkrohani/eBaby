package com.tobeagile.training.ebaby.test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

import com.tobeagile.training.ebaby.domain.Auction;
import com.tobeagile.training.ebaby.domain.User;
import com.tobeagile.training.ebaby.services.AuctionLogger;
import com.tobeagile.training.ebaby.services.AuctionService;
import com.tobeagile.training.ebaby.services.UserService;

public class TestAuctionLogger extends BaseTestClass {
	
	@Test
	public void testAuctionLoggerForAuctionNotStartedYet() {
		bidAmount = 10000.00;
		auction = createTestAuction("Seller1", auctionStartDateTime, auctionEndDateTime);
		
		userService.logIn(buyer);
		auctionService.placeBid(bidAmount, auction, buyer);

		assertEquals(false, logger.findMessage("auctionLog.log", auction.getAuctionId() + isCarAuction));
		assertEquals(false, logger.findMessage("auctionLog.log", auction.getAuctionId() + isOverTenThousand));
	}
	
	@Test
	public void testAuctionLoggerForAuctionClosed() {
		bidAmount = 10000.01;
		auction = createTestAuction("Seller2", auctionStartDateTime, auctionEndDateTime);
		
		userService.logIn(buyer);
		auctionService.changeAuctionState(auction); // Open the auction
		auctionService.changeAuctionState(auction); // Close the auction
		auctionService.onClose(auction);
		
		auctionService.placeBid(bidAmount, auction, buyer);

		assertEquals(false, logger.findMessage("auctionLog.log", auction.getAuctionId() + isCarAuction));
		assertEquals(false, logger.findMessage("auctionLog.log", auction.getAuctionId() + isOverTenThousand));
	}
	
	@Test
	public void testAuctionLoggerForNoLogging() {
		bidAmount = 10000.00;
		auction = createTestAuction("Seller3", auctionStartDateTime, auctionEndDateTime);
		
		auctionService.changeAuctionState(auction);	
		userService.logIn(buyer);
		auctionService.placeBid(bidAmount, auction, buyer);
		auctionService.changeAuctionState(auction);
		auctionService.onClose(auction);

		assertEquals(false, logger.findMessage("auctionLog.log", auction.getAuctionId() + isCarAuction));
		assertEquals(false, logger.findMessage("auctionLog.log", auction.getAuctionId() + isOverTenThousand));
	}
	
	@Test
	public void testAuctionLoggerOnAmountsOnCategoryCarAt10000() {
		bidAmount = 10000.00;
		
		auction = createTestAuction("Seller4", auctionStartDateTime, auctionEndDateTime);

		auctionService.changeAuctionState(auction);	
		auctionService.setAuctionCategory(auction, "CAR");

		userService.logIn(buyer);
		auctionService.placeBid(bidAmount, auction, buyer);
		auctionService.changeAuctionState(auction);
		auctionService.onClose(auction);

		assertEquals(true, logger.findMessage("auctionLog.log", auction.getAuctionId() + isCarAuction));
		assertEquals(false, logger.findMessage("auctionLog.log", auction.getAuctionId() + isOverTenThousand));
	}
	
	@Test
	public void testAuctionLoggerOnAmountsGreaterThan10000() {
		bidAmount = 10000.01;
		
		auction = createTestAuction("Seller5", auctionStartDateTime, auctionEndDateTime);

		auctionService.changeAuctionState(auction);	
		userService.logIn(buyer);
		auctionService.placeBid(bidAmount, auction, buyer);
		auctionService.changeAuctionState(auction);
		auctionService.onClose(auction);

		assertEquals(true, logger.findMessage("auctionLog.log", auction.getAuctionId() + isOverTenThousand));
		assertEquals(false, logger.findMessage("auctionLog.log", auction.getAuctionId() + isCarAuction));
	}
	
	@Test
	public void testAuctionLoggerOnCarsGreaterThan10000() {
		bidAmount = 10000.01;
		
		auction = createTestAuction("Seller6", auctionStartDateTime, auctionEndDateTime);

		auctionService.setAuctionCategory(auction, "CAR");
		auctionService.changeAuctionState(auction);	
		userService.logIn(buyer);
		auctionService.placeBid(bidAmount, auction, buyer);
		auctionService.changeAuctionState(auction);
		auctionService.onClose(auction);

		assertEquals(true, logger.findMessage("auctionLog.log", auction.getAuctionId() + isCarAuction));
		assertEquals(true, logger.findMessage("auctionLog.log", auction.getAuctionId() + isOverTenThousand));
	}
}