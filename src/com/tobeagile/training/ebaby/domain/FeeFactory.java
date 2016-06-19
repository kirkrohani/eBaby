package com.tobeagile.training.ebaby.domain;

import java.util.HashSet;
import java.util.Set;

import com.tobeagile.training.ebaby.domain.Auction.AuctionCategory;
import com.tobeagile.training.ebaby.services.LuxuryTax;

public class FeeFactory {

	public static Set<FeeDecorator> allFees = null;
	
	public static Set<FeeDecorator> getFees(Auction auction)
	{
		allFees = new HashSet<FeeDecorator>();
		allFees.add(new TransactionFee(auction));
		
		if (auction.getAuctionCategory() != AuctionCategory.DOWNLOADABLE_SOFTWARE && auction.getAuctionCategory() != AuctionCategory.CAR)
			allFees.add(new ShippingFee(auction));
		if(auction.getAuctionCategory() == AuctionCategory.CAR)
		{
			allFees.add(new CarShippingFee(auction));
			allFees.add(new LuxuryTax(auction));
		}
		return allFees;		
	}
}
