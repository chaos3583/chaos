package com.chaos.dao.mapper;

import com.chaos.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

	public User login(User user);

	public User selectByUser(User user);
}
