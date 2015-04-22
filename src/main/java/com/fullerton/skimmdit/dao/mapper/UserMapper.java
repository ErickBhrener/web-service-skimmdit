package com.fullerton.skimmdit.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import com.fullerton.skimmdit.representation.User;

public class UserMapper implements ResultSetMapper<User> {
   public User map(int index, ResultSet r, StatementContext ctx)
   throws SQLException {
      return new User(r.getString("username"), r.getString("password"));
   }
}