package com.tobeagile.training.ebaby.test;
import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import com.tobeagile.training.ebaby.domain.Auction;
import com.tobeagile.training.ebaby.domain.User;
import com.tobeagile.training.ebaby.domain.Auction.AuctionCategory;
import com.tobeagile.training.ebaby.services.UserService;

public class TestAuction extends BaseTestClass{
	private Auction auction = null;
	private UserService users = new UserService();
	private String firstNameUser1 = "John";
	private String lastNameUser1 = "Doe";
	private String emailUser1 = "johnDoe@me.com";
	private String passwordUser1 = "myPass";
	private String userNameUser1 = "User1";
	private String description = "This vase is nice.";
	private Double price = 100.00;
	private LocalDateTime auctionStartDateTime = LocalDateTime.now();
	private LocalDateTime auctionEndDateTime = LocalDateTime.now().plusDays(2);

	private User u = users.register(firstNameUser1, lastNameUser1, emailUser1, userNameUser1, passwordUser1);

	@Before
	public void setUp() throws Exception {
		auction = new Auction(u,description,price,auctionStartDateTime,auctionEndDateTime);
	}

	@Test
	public void testAuctionCreation() {
		users.setAsSeller(u);
		
		assertEquals(u.getUserName(), auction.getSeller().getUserName());
		assertEquals(description, auction.getDescription());
		assertEquals(price, auction.getPrice());
		assertEquals(auctionStartDateTime, auction.getAuctionStartDateTime());
		assertEquals(auctionEndDateTime, auction.getAuctionEndDateTime());	
		assertEquals(u.getUserName() + auctionStartDateTime, auction.getAuctionId());
	}
	
	@Test
	public void testNewAuctionStateIsNotStarted() {		
		assertEquals(auction.getAuctionState(), Auction.AuctionState.NOT_STARTED);
	}
	
	
	@Test
	public void testNewAuctionCategory() {		
		assertEquals(auction.getAuctionCategory(), Auction.AuctionCategory.GENERAL);
		auction.setAuctionCategory(AuctionCategory.CAR);
		assertEquals(auction.getAuctionCategory(), Auction.AuctionCategory.CAR);
		auction.setAuctionCategory(AuctionCategory.DOWNLOADABLE_SOFTWARE);
		assertEquals(auction.getAuctionCategory(), Auction.AuctionCategory.DOWNLOADABLE_SOFTWARE);
	}
	
	@Test
	public void testStartAuction() {
		auction.setAuctionState(Auction.AuctionState.OPEN);
		assertEquals(auction.getAuctionState(), Auction.AuctionState.OPEN);
	}
	
	@Test
	public void testCloseAuction() {
		auction.setAuctionState(Auction.AuctionState.CLOSED);
		assertEquals(auction.getAuctionState(), Auction.AuctionState.CLOSED);
	}
}
