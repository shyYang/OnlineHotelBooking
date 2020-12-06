package edu.fudan.onlinehotelbooking.mapper;

import edu.fudan.onlinehotelbooking.core.Mapper;
import edu.fudan.onlinehotelbooking.entity.User;

public interface UserMapper extends Mapper<User> {
   int insertUser(User user);
}

