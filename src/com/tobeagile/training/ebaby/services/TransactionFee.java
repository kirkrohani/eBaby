package com.tobeagile.training.ebaby.services;

import com.tobeagile.training.ebaby.domain.Auction;
import com.tobeagile.training.ebaby.domain.FeeDecorator;

public class TransactionFee extends FeeDecorator {

	private Auction auction = null;
	
	//Currently there is no buyer transcation fee
	Double buyerFee = 0.00;
	Double sellerFee = 0.00;
	
	public TransactionFee(Auction auction)
	{
		this.auction = auction;
		
	}
	
	@Override
	public void process() {
		// 2% of Selling Price
		sellerFee = .98*auction.getPrice();
		auction.setSellerIncome(sellerFee);
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
