package gps.locator.api.resources;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import gps.locator.database.DAL;
import gps.locator.database.Queries;
import gps.locator.model.Address;
import gps.locator.model.User;

@Path("addresses")
public class AddressResource {
	
	public AddressResource(){
		
	}

	@Context
	private SecurityContext securityContext;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Address getAddress(){
		
		User user = (User) securityContext.getUserPrincipal();
		if(user.getAddresses()== null ){
			return null;
		} else if(user.getAddresses().isEmpty() ){
			return null;
		}	
	
		return user.getAddresses().get(0);
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public Address createAddress(Address adr){
		
		DAL db = new DAL();
		db.openSession();
		
		User user = (User) securityContext.getUserPrincipal();
		adr.setUser(user);
		db.save(adr);	
		db.closeSession();
		
		return adr;
		
	}
	
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public Address updateAddress(Address adr){
		
		DAL db = new DAL();
		db.openSession();
		
		User user = (User) securityContext.getUserPrincipal();
		adr.setUser(user);
		
		Long addressId=user.getAddresses().get(0).getAddressId();
		
		adr.setAddressId(addressId);
		
		db.update(adr);	
		db.closeSession();
		
		return adr;
		
	}

	
}
