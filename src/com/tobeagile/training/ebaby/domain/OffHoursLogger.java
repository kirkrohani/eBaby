package com.tobeagile.training.ebaby.domain;


public class OffHoursLogger extends LoggerDecorator {
		
	@Override
	public void process(Auction auction, String fileName) {	
		AuctionLogger logger = AuctionLogger.getInstance();
		logger.log(fileName, auction.getAuctionId() + " closed after hours");
	}
}
