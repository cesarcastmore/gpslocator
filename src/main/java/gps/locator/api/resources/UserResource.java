package gps.locator.api.resources;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import gps.locator.database.DAL;
import gps.locator.model.ErrorMessage;
import gps.locator.model.User;

@Path("users")
public class UserResource {

	public UserResource(){
		
	}

	@Context
	private SecurityContext securityContext;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User getClient() {

		DAL dal= new DAL();
		dal.openSession();
		User user = (User) securityContext.getUserPrincipal();
		dal.closeSession();
		return user;

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User updateClient(User user) {
		
		DAL dal= new DAL();
		dal.openSession();
		
		User userPrincipal = (User) securityContext.getUserPrincipal();
		user.setUserId(userPrincipal.getUserId());

		
		dal.update(user);	
		dal.closeSession();

		return user;
	

	}
	


}
