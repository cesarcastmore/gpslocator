package gps.locator.api.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import gps.locator.database.Queries;
import gps.locator.database.query.ServiceQuery;
import gps.locator.model.Service;;

@Path("services")
public class ServiceResource {
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Service> getClient() {
		return ServiceQuery.getServices();
	}


}
