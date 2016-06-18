package com.tobeagile.training.ebaby.services;
import java.util.HashSet;
import java.util.Set;

import com.tobeagile.training.ebaby.domain.User;

public class UserService
{
	private Set<User> usersCollection = null;

	public UserService()
	{
		usersCollection = new HashSet<User>();
	}

	public User getUser(String userName)
	{
		for (User u : usersCollection)
		{
			if (u.getUserName().equals(userName))
				return u;
		}
		return null;
	}

	public Boolean addUser(User user)
	{
		if (!usersCollection.contains(user))
		{
			usersCollection.add(user);
			return true;
		}
		return false;
	}

	public void logIn(User user)
	{
		user.setLoggedIn(true);
	}

	public void logOut(User user)
	{
		user.setLoggedIn(false);
	}

	public User register(String firstName, String lastName, String email, String userName, String password)
	{
		User user = new User(firstName, lastName, email, userName, password);
		this.addUser(user);
		return user;
	}

	public void setAsSeller(User user)
	{
		user.setSeller();
	}

	public Boolean isSeller(User user)
	{
		return user.isSeller();
	}
}
