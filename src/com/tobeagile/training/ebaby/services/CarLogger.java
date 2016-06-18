package com.tobeagile.training.ebaby.services;

import com.tobeagile.training.ebaby.domain.Auction;

public class CarLogger extends LoggerDecorator {
		
	@Override
	public void process(Auction auction, String fileName) {	
		AuctionLogger logger = AuctionLogger.getInstance();
		logger.log(fileName, auction.getAuctionId() + " is a car auction");
	}
}