package com.tobeagile.training.ebaby.domain;

public class User
{

	private String firstName = null;
	private String lastName = null;
	private String email = null;
	private String password = null;
	private String userName = null;
	private Boolean loggedIn = false;
	private Boolean isSeller = false;

	public User(String firstName, String lastName, String email, String userName, String password)
	{
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.userName = userName;

	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userName == null)
		{
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public Boolean isLoggedIn()
	{
		return loggedIn;
	}

	public void setLoggedIn(Boolean loggedIn)
	{
		this.loggedIn = loggedIn;
	}

	public Boolean isSeller()
	{
		return isSeller;
	}

	public void setSeller()
	{
		isSeller = true;
	}

}
