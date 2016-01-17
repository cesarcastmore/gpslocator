package gps.locator.api.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import gps.locator.database.DAL;
import gps.locator.model.Request;
import gps.locator.model.User;
import gps.locator.model.UserService;

@Path("userservices")
public class UserServiceResource {

	public UserServiceResource(){
		System.out.println("ENTRO AQUI");

	}

	@Context
	private SecurityContext securityContext;
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserService> getUserServices(){
		
		System.out.println("ENTRO AQUI");
		
		User user = (User) securityContext.getUserPrincipal();
		if(user.getType().equals("business")){
			
			return user.getUserServices();
			
		
		}  else return new ArrayList<UserService>();
	
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserService createUserService(UserService userservice){

		
		User user = (User) securityContext.getUserPrincipal();
		
		if(user.getType().equals("business")){
			DAL db = new DAL();
			db.openSession();
			userservice.setUser(user);
			
			db.save(userservice);	
			db.closeSession();
			
		}
		return userservice;
		
	}
	
	
	@DELETE
	@Path("/{userServiceId}")

	public void remove(@PathParam("userServiceId") Long userServiceId){
		DAL db = new DAL();
		db.openSession();
		
		UserService userService = (UserService) db.get(UserService.class, userServiceId);

		
		db.delele(userService);
		db.closeSession();
		
	}
	
}
