package com.tobeagile.training.ebaby.services;

import com.tobeagile.training.ebaby.domain.Auction;
import com.tobeagile.training.ebaby.domain.FeeDecorator;

public class LuxuryTax extends FeeDecorator {

	private Auction auction = null;
	
	//Currently there is no buyer transcation fee
	Double buyerFee = 0.00;
	Double sellerFee = 0.00;
	
	public LuxuryTax(Auction auction)
	{
		this.auction = auction;
	}
	
	@Override
	public void process() {
		// 2% of Selling Price
		Double price = auction.getPrice();
		if (price > 50000.00)
			buyerFee = .04*price;
	}

	@Override
	public Double getBuyerFee() {
		return buyerFee;
	}

	public void setBuyerFee(Double buyerFee) {
		this.buyerFee = buyerFee;
	}

	@Override
	public Double getSellerFee() {
		return sellerFee;
	}

	public void setSellerFee(Double sellerFee) {
		this.sellerFee = sellerFee;
	}
}
