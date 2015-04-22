package com.fullerton.skimmdit.resources;

import com.fullerton.skimmdit.representation.Link;
import com.fullerton.skimmdit.representation.User;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.skife.jdbi.v2.DBI;
import com.fullerton.skimmdit.dao.LinkDao;
import java.net.URI;
import java.net.URISyntaxException;
import io.dropwizard.auth.Auth;
import java.util.List;

@Path("/link")
@Produces(MediaType.APPLICATION_JSON)
public class LinkResource {

    private final LinkDao linkdao;

    public LinkResource(DBI jdbi) {
        linkdao = jdbi.onDemand(LinkDao.class);
    }

    @GET
    @Path("/{id}")
    public Response getLink(@PathParam("id") int id, @Auth Boolean isAuthenticated) {
        // retrieve information about the contact with the provided id
        Link link = linkdao.getLinkById(id);
        return Response
                .ok(link)
                .build();
    }

    @GET
    @Path("/all")
    public Response getAllLinks(@Auth Boolean isAuthenticated){
        List<Link> links = linkdao.getAllLinks();
        return Response.ok(links).build();
    }

    @GET
    @Path("/byuser/{username}")
    public Response getLinksByUsername(@PathParam("username") String username, @Auth Boolean isAuthenticated){
        List<Link> links = linkdao.getLinkByUsername(username);
        return Response.ok(links).build();
    }    

    @POST
    public Response createLink(Link link, @Auth Boolean isAuthenticated) throws URISyntaxException {
        // store the new contact
        int linkId = linkdao.createLink(link.getTitle(),link.getDescription(),link.getUrl(),link.getVotes(),link.getUsername());
        return Response.created(new URI(String.valueOf(linkId))).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteLink(@PathParam("id") int id, @Auth Boolean isAuthenticated) {
        // delete the contact with the provided id
        linkdao.deleteLink(id);
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}")
    public Response updateLink(@PathParam("id") int id, Link link, @Auth Boolean isAuthenticated) {
        // update the contact with the provided ID
        Link old_link = linkdao.getLinkById(id);
        linkdao.updateLink(id, link.getTitle(),link.getDescription(),link.getUrl(),old_link.getVotes(),link.getUsername());
        return Response.ok(
                new Link(id, link.getTitle(),link.getDescription(),link.getUrl(),old_link.getVotes(),link.getUsername())).build();
    }

    @PUT
    @Path("/upvote/{id}")
    public Response upVote(@PathParam("id") int id, @Auth Boolean isAuthenticated) {
        // update the contact with the provided ID
        Link link = linkdao.getLinkById(id);
        if(link!=null){
            System.out.println(link.getVotes());
            linkdao.updateLink(id, link.getTitle(),link.getDescription(),link.getUrl(),link.getVotes() + 1,link.getUsername());
        }
        return Response.noContent().build();
    }

    @PUT
    @Path("/downvote/{id}")
    public Response downVote(@PathParam("id") int id, @Auth Boolean isAuthenticated) {
        // update the contact with the provided ID
        Link link = linkdao.getLinkById(id);
        if(link!=null && link.getVotes()>0){
            linkdao.updateLink(id, link.getTitle(),link.getDescription(),link.getUrl(),link.getVotes() - 1,link.getUsername());
        }
        return Response.noContent().build();
    }
}