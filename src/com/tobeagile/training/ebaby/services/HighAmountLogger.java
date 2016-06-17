package com.tobeagile.training.ebaby.services;

public class HighAmountLogger extends LoggerDecorator {
	
	@Override
	public void process() {	
		AuctionLogger logger = AuctionLogger.getInstance();
		logger.log("c:\\auctionLog.log", "auction for amount over $10000.00");
	}
}
