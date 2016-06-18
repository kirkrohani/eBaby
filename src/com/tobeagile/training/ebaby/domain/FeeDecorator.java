package com.tobeagile.training.ebaby.domain;

public abstract class FeeDecorator {
	public abstract void process();
	public abstract Double getBuyerFee();
	public abstract Double getSellerFee();
}
