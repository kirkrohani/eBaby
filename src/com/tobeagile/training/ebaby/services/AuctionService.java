package com.tobeagile.training.ebaby.services;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.tobeagile.training.ebaby.domain.Auction;
import com.tobeagile.training.ebaby.domain.User;


public class AuctionService {
	private Set<Auction> auctions = null;
	
	public AuctionService()
	{
		auctions = new HashSet<Auction>();
	}

	public Auction createAuction(User u, String description, Double price,
			LocalDateTime auctionStartDateTime, LocalDateTime auctionEndDateTime) {
		if(u.isLoggedIn() && u.isSeller() && validationAuctionTimes(auctionStartDateTime, auctionEndDateTime))
		{
			Auction auction = new Auction(u,description,price,auctionStartDateTime,auctionEndDateTime);
			auctions.add(auction);
			return auction;
		}		
		return null;
	}
	
		
	private boolean validationAuctionTimes(LocalDateTime auctionStartTime, LocalDateTime auctionEndTime)
	{
		System.out.println("Start time :"+auctionStartTime);
		System.out.println("End time :"+auctionEndTime);
		System.out.println("Now time :"+LocalDateTime.now());
		if (auctionStartTime.equals(LocalDateTime.now()))
				System.out.println("YES");
		if(auctionEndTime.isAfter(auctionStartTime) && auctionStartTime.isAfter(LocalDateTime.now()))
			return true;
		return false;
	}
	


}
