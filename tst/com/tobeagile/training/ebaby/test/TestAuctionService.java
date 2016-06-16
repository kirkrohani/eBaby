package com.tobeagile.training.ebaby.test;
import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.tobeagile.training.ebaby.domain.Auction;
import com.tobeagile.training.ebaby.domain.User;
import com.tobeagile.training.ebaby.services.AuctionService;
import com.tobeagile.training.ebaby.services.UserService;


public class TestAuctionService {

	@Test
	public void testAddAuctionToAuctions() {
		UserService users = new UserService();
		String firstNameUser1 = "John";
		String lastNameUser1 = "Doe";
		String emailUser1 = "johnDoe@me.com";
		String passwordUser1 = "myPass";
		String userNameUser1 = "User1";
		
		User u = users.register(firstNameUser1, lastNameUser1, emailUser1, userNameUser1, passwordUser1);
		String description = "This vase is nice.";
		Double price = 100.00;
		LocalDateTime auctionStartDateTime = LocalDateTime.now().plusSeconds(30);
		LocalDateTime auctionEndDateTime = LocalDateTime.now().plusDays(2);
		
		users.logIn(u);
		users.setAsSeller(u);
		
		AuctionService auctions = new AuctionService();
		Auction auction = auctions.createAuction(u,description,price,auctionStartDateTime,auctionEndDateTime);
		assertEquals(u.getUserName() + auctionStartDateTime, auction.getAuctionId());
	}

}
