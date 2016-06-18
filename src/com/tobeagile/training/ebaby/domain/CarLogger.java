package com.tobeagile.training.ebaby.domain;


public class CarLogger extends LoggerDecorator {
		
	@Override
	public void process(Auction auction, String fileName) {	
		AuctionLogger logger = AuctionLogger.getInstance();
		logger.log(fileName, auction.getAuctionId() + " is a car auction");
	}
}
