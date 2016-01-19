package gps.locator.api.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import gps.locator.database.DAL;
import gps.locator.database.Queries;
import gps.locator.database.query.CategoryMenuQuery;
import gps.locator.model.Address;
import gps.locator.model.CategoryMenu;
import gps.locator.model.ErrorMessage;
import gps.locator.model.Permission;
import gps.locator.model.User;

@Path("credentials")
public class CredentialResource {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User setClient(User user, @Context UriInfo urlInfo) {
		
		if(user.getType() == null){
			throw new WebApplicationException(
					new ErrorMessage("El tipo de usuario incorrecto no debe  estar en blanco", 400, "El usuario debe de ser client o business").toResponse());
		}
		
		if(!(user.getType().equals("client") || user.getType().equals("business"))){
			throw new WebApplicationException(
					new ErrorMessage("El tipo de usuario incorrecto", 400, "El usuario debe de ser client o business").toResponse());
		}
		
		DAL dal= new DAL();
		dal.openSession();
		
		String url= urlInfo.getBaseUri().toString().replaceAll("api/", "images/user.jpg");
		user.setProfileimage(url);
		user.setProfileimage(url);
		
		if(user.getType().equals("client")){
			List<CategoryMenu> caregories = CategoryMenuQuery.getCategoryMenyByType("client");
			
			
			for(CategoryMenu cat:caregories){
				Permission per= new Permission();
				per.setCategoryMenu(cat);
				per.setName("client_" + user.getUsername());
				per.setUser(user);
				dal.save(per);
				
			}
			
		} else if(user.getType().equals("business")){
			
		
			
		List<CategoryMenu> caregories = CategoryMenuQuery.getCategoryMenyByType("business");
			
			
			for(CategoryMenu cat:caregories){
				Permission per= new Permission();
				per.setCategoryMenu(cat);
				per.setName("business_" + user.getUsername());
				per.setUser(user);
				dal.save(per);

				
			}
			
		}
		
		
		for(Address adr: user.getAddresses()){
			adr.setUser(user);
			dal.save(adr);
			
		}
		
		
		
		

		
		dal.save(user);
		dal.closeSession();

		return user;
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User authetication(@QueryParam("username") String username, @QueryParam("password") String password  ) {
		
		User user = Queries.getUser(username, password);
		
		if(user == null){
			throw new WebApplicationException(
					new ErrorMessage("El usuario o el password es incorrecto", 400, "El usuario debe de ser client o business").toResponse());
		}
		
		user.setEmail(null);
		user.setUsername(null);
		user.setPassword(null);
		
		return user;
		
	}
		
	


}
