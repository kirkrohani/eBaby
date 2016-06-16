package com.tobeagile.training.ebaby.services;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.tobeagile.training.ebaby.domain.Auction;
import com.tobeagile.training.ebaby.domain.Auction.AuctionState;
import com.tobeagile.training.ebaby.domain.AuctionNotifier;
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
		if(auctionEndTime.isAfter(auctionStartTime) && auctionStartTime.isAfter(LocalDateTime.now()))
			return true;
		return false;
	}

	
	public void changeAuctionState(Auction auction) {
		
		if(auction.getAuctionState().equals(AuctionState.NOT_STARTED))
		{
			auction.setAuctionState(AuctionState.OPEN);
		}
		else if(auction.getAuctionState().equals(AuctionState.OPEN))
		{
			auction.setAuctionState(AuctionState.CLOSED);
			sendNotifications(auction);
		}		
	}

	public void placeBid(Double bidAmount, Auction auction, User bidder) {
		
		if (!bidder.equals(auction.getSeller()))
		{
			if(bidAmount >= auction.getPrice() )
			{
				auction.setPrice(bidAmount);
				auction.setHighBidder(bidder);
			}
		}
	}

	public void sendNotifications(Auction auction) {
		AuctionNotifier notifier = AuctionNotifier.getInstance(auction);
		notifier.sendMessage(auction);
	}
}
