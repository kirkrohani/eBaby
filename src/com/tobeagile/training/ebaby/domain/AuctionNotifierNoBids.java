package com.tobeagile.training.ebaby.domain;

import com.tobeagile.training.ebaby.services.PostOffice;

public class AuctionNotifierNoBids extends AuctionNotifier {
	
	private Auction auction = null;
	private String sellerMessageItemNotSold = null;
			
	protected AuctionNotifierNoBids(Auction auction)
	{
		this.auction = auction;
		sellerMessageItemNotSold = String.format("Sorry, your aution for %s  did not have any bidders", 
				auction.getDescription());
	}
	
	@Override
	public void sendMessage(Auction auction)
	{
		PostOffice usps = PostOffice.getInstance();
		usps.sendEMail(auction.getSeller().getEmail(), sellerMessageItemNotSold);
	}
}
