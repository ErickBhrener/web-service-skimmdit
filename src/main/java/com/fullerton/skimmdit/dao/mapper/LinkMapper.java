package com.fullerton.skimmdit.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import com.fullerton.skimmdit.representation.Link;

public class LinkMapper implements ResultSetMapper<Link> {
   public Link map(int index, ResultSet r, StatementContext ctx)
   throws SQLException {
      return new Link(
      r.getInt("id"), r.getString("title"),
      r.getString("description"),r.getString("url"),r.getInt("votes"),r.getString("username"));
   }
}