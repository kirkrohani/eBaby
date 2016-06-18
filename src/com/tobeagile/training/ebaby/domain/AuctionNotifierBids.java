package com.tobeagile.training.ebaby.domain;

import com.tobeagile.training.ebaby.services.PostOffice;

public class AuctionNotifierBids extends AuctionNotifier 
{
	private String highestBidderEmail = null;
	
	protected AuctionNotifierBids(Auction auction)
	{
		Double shippingCost = 0.00;
		Double luxuryTax = 0.00;
		if(auction.getAuctionCategory().equals("DOWNLOADABLE_SOFTWARE"))
		{
			shippingCost = 0.00;		
		}
		else if(auction.getAuctionCategory().equals("CAR"))
		{
			shippingCost = 1000.00;
			if(auction.getPrice() > 50000.00)
				luxuryTax = auction.getPrice() * 0.04;
		}
		else
			shippingCost = 10.00;

		highestBidderEmail = String.format("Congratulations! You won an autions for a %s from %s for %s", 
				auction.getDescription(), auction.getSeller().getEmail(), auction.getPrice() + shippingCost + luxuryTax);
	}
	
	@Override
	public void sendMessage(Auction auction)
	{
		Double yourIncome = auction.getPrice() * 0.98;
		String sellerMessageItemSold = 
				String.format("Your %s aution sold to %s for %s", 
						auction.getDescription(), auction.getHighBidder().getEmail(), yourIncome);

		System.out.println(sellerMessageItemSold);
		PostOffice usps = PostOffice.getInstance();
		usps.sendEMail(auction.getSeller().getEmail(), sellerMessageItemSold);
		usps.sendEMail(auction.getHighBidder().getEmail(), highestBidderEmail);
	}
}
