package com.fullerton.skimmdit.resources;

import com.fullerton.skimmdit.representation.User;
import com.fullerton.skimmdit.dao.UserDao;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.skife.jdbi.v2.DBI;
import java.net.URI;
import java.net.URISyntaxException;
import io.dropwizard.auth.Auth;
import java.util.List;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

	@Context
	UriInfo uriInfo;

	private final UserDao usrdao;
	public UserResource(DBI jdbi){
		usrdao = jdbi.onDemand(UserDao.class);
	}
	@GET
	@Path("/{username}")
	public Response getUser(@PathParam("username") String username, @Auth Boolean isAuthenticated){
		User user = usrdao.getUser(username);
		return Response.ok(user).build();
	}
	@GET
	@Path("/all")
	public Response getAllUsers(@Auth Boolean isAuthenticated){
		List<User> users = usrdao.getAllUsers();
		return Response.ok(users).build();
	}

	@POST
	public Response createUser(User user, @Auth Boolean isAuthenticated) throws URISyntaxException
	{
		usrdao.createUser(user.getUsername(),user.getPassword());
		return Response.created(new URI(user.getUsername())).build();
	}

	@DELETE
	@Path("/{username}")
	public Response deleteUser(@PathParam("username") String username, @Auth Boolean isAuthenticated){
		usrdao.deleteUser(username);
		return Response.noContent().build();
	}

	@PUT
	@Path("/{username}")
	public Response updateUser(@PathParam("username") String username, User user, @Auth Boolean isAuthenticated){
		usrdao.updateUser(username, user.getUsername(), user.getPassword());
		return Response.ok(user).build();
	}
}