package com.fullerton.skimmdit.dao;

import com.fullerton.skimmdit.representation.Link;
import com.fullerton.skimmdit.dao.mapper.LinkMapper;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import java.util.List;

public interface LinkDao {

    @Mapper(LinkMapper.class)
    @SqlQuery("select * from link where id = :id")
    Link getLinkById(@Bind("id") int id);

	@Mapper(LinkMapper.class)
    @SqlQuery("select * from link")
  	List<Link> getAllLinks();

  	@Mapper(LinkMapper.class)
	@SqlQuery("select * from link where username = :username")
  	List<Link> getLinkByUsername(@Bind("username") String username);

    @GetGeneratedKeys
    @SqlUpdate("insert into link (id, title, description, url,votes,username) values (NULL, :title, :description, :url, :votes, :username)")
    int createLink(@Bind("title") String title, @Bind("description") String description, @Bind("url") String url, @Bind("votes") int votes,@Bind("username") String username);

    @SqlUpdate("update link set title = :title, description = :description, url = :url, votes = :votes where id = :id")
    void updateLink(@Bind("id") int id, @Bind("title") String title, @Bind("description") String description, @Bind("url") String url, @Bind("votes") int votes,@Bind("username") String username);

    @SqlUpdate("delete from link where id = :id")
    void deleteLink(@Bind("id") int id);
}