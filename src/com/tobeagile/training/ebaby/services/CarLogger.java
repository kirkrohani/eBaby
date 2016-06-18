package com.tobeagile.training.ebaby.services;

import com.tobeagile.training.ebaby.domain.Auction;

public class CarLogger extends LoggerDecorator {
		
	@Override
	public void process(Auction auction) {	
		AuctionLogger logger = AuctionLogger.getInstance();
		logger.log("auctionLog.log", auction.getAuctionId() + " is a car auction");
	}
}
