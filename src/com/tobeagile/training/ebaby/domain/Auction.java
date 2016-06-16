package com.tobeagile.training.ebaby.domain;
import java.time.LocalDateTime;

public class Auction
{
	private String auctionId = null;
	private User seller = null;
	private String description = null;
	private Double price = null;
	private LocalDateTime auctionStartDateTime = null;
	private LocalDateTime auctionEndDateTime = null;

	public Auction(User u, String description, Double price, LocalDateTime auctionStartDateTime,
			LocalDateTime auctionEndDateTime)
	{
		setAuctionId(u.getUserName() + auctionStartDateTime);
		seller = u;
		this.description = description;
		this.price = price;
		this.auctionStartDateTime = auctionStartDateTime;
		this.auctionEndDateTime = auctionEndDateTime;
	}

	public User getSeller()
	{
		return this.seller;
	}

	public String getDescription()
	{
		return this.description;
	}

	public Double getPrice()
	{
		return this.price;
	}

	public LocalDateTime getAuctionStartDateTime()
	{
		return this.auctionStartDateTime;
	}

	public LocalDateTime getAuctionEndDateTime()
	{
		return this.auctionEndDateTime;
	}

	public String getAuctionId()
	{
		return auctionId;
	}

	public void setAuctionId(String auctionId)
	{
		this.auctionId = auctionId;
	}

	@Override
	public String toString()
	{
		return "Auction [auctionId=" + auctionId + ", seller=" + seller + ", description=" + description + ", price="
				+ price + ", auctionStartDateTime=" + auctionStartDateTime + ", auctionEndDateTime="
				+ auctionEndDateTime + "]";
	}

}
