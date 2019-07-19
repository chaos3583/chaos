package com.chaos.service;

import com.chaos.model.User;

public interface UserService {

	public User login(User user);

	public User selectByUser(User user);

}
