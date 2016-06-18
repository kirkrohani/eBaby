package com.tobeagile.training.ebaby.services;

import com.tobeagile.training.ebaby.domain.Auction;
import com.tobeagile.training.ebaby.domain.FeeDecorator;

public class ShippingFee extends FeeDecorator {

	private Auction auction = null;
	
	//Currently there is no buyer transaction fee
	Double buyerFee = 0.00;
	Double sellerFee = 0.00;
	
	public ShippingFee(Auction auction)
	{
		this.auction = auction;
	}
	
	@Override
	public void process() {	
		buyerFee=10.00;
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
