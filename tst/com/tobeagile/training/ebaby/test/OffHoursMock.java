package com.tobeagile.training.ebaby.test;

import com.tobeagile.training.ebaby.services.Hours;

public class OffHoursMock implements Hours
{
	private OffHoursMock() {
		Math.random();
	}
	public static OffHoursMock getInstance() {
		return new OffHoursMock();
	}
	
	public boolean isOffHours() {
		return true;
	}
}

