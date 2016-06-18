package com.tobeagile.training.ebaby.services;

import java.util.HashSet;
import java.util.Set;

import com.tobeagile.training.ebaby.domain.Auction;

public class LoggerFactory {

	public static Set<LoggerDecorator> allLogs = null;
	
	public static Set<LoggerDecorator> getLogs(Auction auction)
	{		
		allLogs = new HashSet<>();
		
		if (auction.getAuctionCategory().equals("CAR"))
		{
			allLogs.add(new CarLogger());
		}
		if (auction.getPrice() > 10000){
			allLogs.add(new HighAmountLogger());
		}
		if(auction.isClosedOffHours())
		{
			allLogs.add(new OffHoursLogger());
		}
		return allLogs;		
	}
}
