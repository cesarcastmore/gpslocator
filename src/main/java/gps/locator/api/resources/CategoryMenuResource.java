package gps.locator.api.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import gps.locator.database.DAL;
import gps.locator.database.Queries;
import gps.locator.model.CategoryMenu;
import gps.locator.model.User;

@Path("categorymenus")
public class CategoryMenuResource {

	public CategoryMenuResource() {

	}
	


	@Context
	private SecurityContext securityContext;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<CategoryMenu> getCategoryMenus() {

		User user = (User) securityContext.getUserPrincipal();

		if (user.getPermissions() == null) {
			return new ArrayList<CategoryMenu>();
		}

		List<CategoryMenu> categoryMenus = Queries.getCategoryMenuByUser(user);
		return categoryMenus;
	}
	
	

	@GET
	@Path("/{categoryMenuId}")
	@Produces(MediaType.APPLICATION_JSON)
	public CategoryMenu getCategoryMenu(@PathParam("categoryMenuId") Long categoryMenuId, @Context UriInfo uriInfo ) {
		
		DAL db= new DAL();
		
		db.openSession();
		CategoryMenu ctm = (CategoryMenu) db.get(CategoryMenu.class, categoryMenuId);
		
		
		db.closeSession();
		
		String self_uri =  "/categorymenus/"+ Long.toString(categoryMenuId);
		ctm.addLink(self_uri, "self");


		return ctm;

		
	}
	
	

}
