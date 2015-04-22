package com.fullerton.skimmdit.dao;

import com.fullerton.skimmdit.representation.User;
import com.fullerton.skimmdit.dao.mapper.UserMapper;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import java.util.List;

public interface UserDao{

	@Mapper(UserMapper.class)
    @SqlQuery("select * from user where username = :username")
    User getUser(@Bind("username") String username);

   	@Mapper(UserMapper.class)
    @SqlQuery("select * from user")
    List<User> getAllUsers();

    @SqlUpdate("insert into user (username, password) values (:username, :password)")
    int createUser(@Bind("username") String username, @Bind("password") String password);

    @SqlUpdate("update user set username = :new_username, password = :password where username = :username")
    void updateUser(@Bind("username") String username, @Bind("new_username") String new_username, @Bind("password") String password);

    @SqlUpdate("delete from user where username = :username")
    void deleteUser(@Bind("username") String username);
	
}