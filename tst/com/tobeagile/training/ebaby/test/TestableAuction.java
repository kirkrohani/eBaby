package com.tobeagile.training.ebaby.test;

import java.time.LocalDateTime;

import com.tobeagile.training.ebaby.domain.Auction;
import com.tobeagile.training.ebaby.domain.User;

public class TestableAuction extends Auction {
	
	public boolean closedOffHours = true;

	public TestableAuction(User u, String description, Double price,
			LocalDateTime auctionStartDateTime, LocalDateTime auctionEndDateTime) {
		super(u, description, price, auctionStartDateTime, auctionEndDateTime);
	}

	@Override
	public boolean isClosedOffHours() {
		return closedOffHours;
	}

	public void setClosedOffHours(boolean closedOffHours) {
		OffHoursMock hoursMock = OffHoursMock.getInstance();
		this.closedOffHours = hoursMock.isOffHours();
	}
}
