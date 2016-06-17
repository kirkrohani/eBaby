package com.tobeagile.training.ebaby.domain;

public abstract class AuctionNotifier 
{
	private static AuctionNotifier instance;
	
	protected AuctionNotifier()
	{
		
	}
	public static AuctionNotifier getInstance(Auction auction) {
		if (auction.getHighBidder() == null)
		{
			return new AuctionNotifierNoBids(auction);			
		}
		else
		{
			return new AuctionNotifierBids(auction);
		}
	}
	
	public abstract void sendMessage(Auction auction);
}
