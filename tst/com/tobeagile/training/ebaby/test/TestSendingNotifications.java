package com.tobeagile.training.ebaby.test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

import com.tobeagile.training.ebaby.domain.Auction;
import com.tobeagile.training.ebaby.domain.User;
import com.tobeagile.training.ebaby.services.AuctionService;
import com.tobeagile.training.ebaby.services.PostOffice;
import com.tobeagile.training.ebaby.services.UserService;

public class TestSendingNotifications extends BaseTestClass {
	
	@Test
	public void testNotificationsOnNoBidAuction() {
		auction = createTestAuction("Seller1", auctionStartDateTime, auctionEndDateTime);
		auctionService.changeAuctionState(auction); // opens it
		auctionService.changeAuctionState(auction); // closes it
		auctionService.onClose(auction);
		
		PostOffice usps = PostOffice.getInstance();
		String message = usps.findEmail(auction.getSeller().getEmail(), "Sorry");
		assertEquals(true, message.contains("Sorry") );
	}
	
	@Test
	public void testNotificationsOnBidAuction() {
		bidAmount = 20.00;
		
		PostOffice usps = PostOffice.getInstance();
		usps.clear();
		
		auction = createTestAuction("Seller1", auctionStartDateTime, auctionEndDateTime);
		auctionService.changeAuctionState(auction);		
		userService.logIn(buyer);
		auctionService.placeBid(bidAmount, auction, buyer);
		auctionService.setAuctionCategory(auction, "GENERAL");
		auctionService.changeAuctionState(auction);
		auctionService.onClose(auction);

		String sellerMessage = usps.findEmail(auction.getSeller().getEmail(), "sold");
		String buyerMessage = usps.findEmail(buyer.getEmail(), "Congratulations");
		
		assertEquals(true,sellerMessage.contains("sold") );
		assertEquals(true,buyerMessage.contains("Congratulations") );
	}
	
	@Test
	public void testNotificationsOnBidAuctionAmountsOnCategoryCar() {
		bidAmount = 20.00;
		
		PostOffice usps = PostOffice.getInstance();
		usps.clear();
		
		auction = createTestAuction("Seller1", auctionStartDateTime, auctionEndDateTime);
		auctionService.changeAuctionState(auction);	
		userService.logIn(buyer);
		auctionService.placeBid(bidAmount, auction, buyer);
		auctionService.setAuctionCategory(auction, "CAR");
		auctionService.changeAuctionState(auction);
		auctionService.onClose(auction);

		String sellerMessage = usps.findEmail(auction.getSeller().getEmail(), "sold");
		String buyerMessage = usps.findEmail(buyer.getEmail(), "Congratulations");

		assertEquals(true,sellerMessage.contains("19.6"));
		assertEquals(true,buyerMessage.contains("1020.0"));
	}
		
	@Test
	public void testNotificationsOnBidAuctionAmountsOnCategoryCarAt50000() {
		bidAmount = 50000.00;
		
		PostOffice usps = PostOffice.getInstance();
		usps.clear();
		
		auction = createTestAuction("Seller1", auctionStartDateTime, auctionEndDateTime);
		auctionService.changeAuctionState(auction);	
		userService.logIn(buyer);
		auctionService.placeBid(bidAmount, auction, buyer);
		auctionService.setAuctionCategory(auction, "CAR");
		auctionService.changeAuctionState(auction);
		auctionService.onClose(auction);

		String sellerMessage = usps.findEmail(auction.getSeller().getEmail(), "sold");
		String buyerMessage = usps.findEmail(buyer.getEmail(), "Congratulations");
		
		assertEquals(true,sellerMessage.contains("49000.0"));
		assertEquals(true,buyerMessage.contains("51000.0"));
	}

	@Test
	public void testNotificationsOnBidAuctionAmountsOnCategoryCarLuxuryAt5000001() {
		bidAmount = 50000.01;
		
		PostOffice usps = PostOffice.getInstance();
		usps.clear();
		
		auction = createTestAuction("Seller1", auctionStartDateTime, auctionEndDateTime);
		
		auctionService.changeAuctionState(auction);	
		userService.logIn(buyer);
		auctionService.placeBid(bidAmount, auction, buyer);
		auctionService.setAuctionCategory(auction, "CAR");
		auctionService.changeAuctionState(auction);
		auctionService.onClose(auction);
		
		String sellerMessage = usps.findEmail(auction.getSeller().getEmail(), "sold");
		String buyerMessage = usps.findEmail(buyer.getEmail(), "Congratulations");
		assertEquals(true,sellerMessage.contains("49000.00"));
		assertEquals(true,buyerMessage.contains("53000.01"));
	}
}