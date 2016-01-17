package gps.locator.api.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import gps.locator.database.DAL;
import gps.locator.database.query.AddressQuery;
import gps.locator.model.Address;
import gps.locator.model.Request;
import gps.locator.model.User;

@Path("business")
public class BusinessResource {

	@Context
	private SecurityContext securityContext;

	@GET
	@Path("/{businessId}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("businessId") Long userId) {

		User user = (User) securityContext.getUserPrincipal();

		if (user.getType().equals("client")) {
			DAL db = new DAL();
			db.openSession();

			User business = (User) db.get(User.class, userId);
			db.closeSession();
			
			business.setPassword(null);
			
			System.out.println("ENTRO AQUI BUSINESS");

			return business;
		}

		return null;

	}

	@GET
	@Path("/{businessId}/address")
	@Produces(MediaType.APPLICATION_JSON)
	public Address getAddress(@PathParam("businessId") Long userId) {

		User user = (User) securityContext.getUserPrincipal();
		System.out.println("ENTRO AQUI BUSINESS/ADDRESS");


		if (user.getType().equals("client")) {
			User business = new User();
			business.setUserId(userId);
			Address address = AddressQuery.getAddressByUser(business);
			return address;
		}

		return null;

	}

}
