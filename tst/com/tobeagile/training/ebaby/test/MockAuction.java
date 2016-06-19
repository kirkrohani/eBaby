package com.tobeagile.training.ebaby.test;

import java.time.LocalDateTime;

import com.tobeagile.training.ebaby.domain.Auction;
import com.tobeagile.training.ebaby.domain.User;

public class MockAuction extends Auction {
	
	public boolean closedOffHours = true;

	public MockAuction(User u, String description, Double price,
			LocalDateTime auctionStartDateTime, LocalDateTime auctionEndDateTime) {
		super(u, description, price, auctionStartDateTime, auctionEndDateTime);
	}

	@Override
	public boolean isClosedOffHours() {
		return closedOffHours;
	}

	public void setClosedOffHours(boolean offHoursFlag) {
		this.closedOffHours = offHoursFlag;
	}