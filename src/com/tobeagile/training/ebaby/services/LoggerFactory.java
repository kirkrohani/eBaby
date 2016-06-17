package com.tobeagile.training.ebaby.services;

import java.util.HashSet;
import java.util.Set;

import com.tobeagile.training.ebaby.domain.Auction;

public class LoggerFactory {

	private static LoggerDecorator instance;
	public static Set<LoggerDecorator> allLogs = new HashSet<>();
	
	public static Set<LoggerDecorator> getLogs(Auction auction)
	{		
		if (auction.getAuctionCategory().equals("CAR"))
		{
			allLogs.add(new CarLogger());
		}
		if (auction.getPrice() > 10000){
			allLogs.add(new HighAmountLogger());
		}
		return allLogs;
		
		
	}
}
