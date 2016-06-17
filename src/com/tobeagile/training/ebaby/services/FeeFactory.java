package com.tobeagile.training.ebaby.services;

import java.util.HashSet;
import java.util.Set;

import com.tobeagile.training.ebaby.domain.Auction;
import com.tobeagile.training.ebaby.domain.FeeDecorator;

public class FeeFactory {

	private static FeeFactory instance;
	public static Set<FeeDecorator> allFees = new HashSet<>();
	
	public static Set<FeeDecorator> getFees(Auction auction)
	{
	
		allFees.add(new TransactionFee(auction));
		
		if (!auction.getAuctionCategory().equals("DOWNLOADABLE_SOFTWARE") && !auction.getAuctionCategory().equals("CAR"))
			allFees.add(new ShippingFee(auction));
		if(auction.getAuctionCategory().equals("CAR"))
		{
			allFees.add(new CarShippingFee(auction));
			allFees.add(new LuxuryTax(auction));
		}

		return allFees;
		
		
	}
}
