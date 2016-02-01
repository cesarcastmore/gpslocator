package gps.locator.api.resources;

/*
 * Para enviar mensajes sms
 * https://www.cmtelecom.com/pricing/sms
 * 
 */
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import gps.locator.api.google.ClientGoogle;
import gps.locator.api.google.Location;
import gps.locator.api.google.Place;
import gps.locator.api.google.ResponsePlace;
import gps.locator.api.google.ResponseSearch;
import gps.locator.calc.Coordinate;
import gps.locator.calc.DegreeCoordinate;
import gps.locator.calc.EarthCalc;
import gps.locator.calc.Point;
import gps.locator.database.query.AddressQuery;
import gps.locator.database.query.UserQuery;
import gps.locator.model.Address;
import gps.locator.model.User;

@Path("place")
public class PlaceResource {

	@Context
	private SecurityContext securityContext;

	@GET
	@Path("/nearbysearch")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseSearch nearbysearch(@QueryParam("radius") Long radius, @QueryParam("name") String name,
			@QueryParam("types") String types) {

		User user = (User) securityContext.getUserPrincipal();

		if (user.getType().equals("business")) {

			ClientGoogle client = new ClientGoogle();
			Address address = AddressQuery.getAddressByUser(user);
			ResponseSearch search = client.nearBySearch(new Location(address.getLatitude(), address.getLongitude()),
					radius, name, types);
			return search;

		} else if (user.getType().equals("client")) {

			ClientGoogle client = new ClientGoogle();
			Address address = AddressQuery.getAddressByUser(user);
			ResponseSearch search = client.nearBySearch(new Location(address.getLatitude(), address.getLongitude()),
					radius, name, types);

			Coordinate latOrigen = new DegreeCoordinate(address.getLatitude());
			Coordinate lngOrigen = new DegreeCoordinate(address.getLongitude());
			Point pointOrigen = new Point(latOrigen, lngOrigen);

			for (int i = 0; i < search.getResults().size(); i++) {

				Coordinate latDes = new DegreeCoordinate(
						search.getResults().get(i).getGeometry().getLocation().getLat());
				Coordinate lngDes = new DegreeCoordinate(
						search.getResults().get(i).getGeometry().getLocation().getLng());
				Point pointDest = new Point(latDes, lngDes);

				double distance = EarthCalc.getDistance(pointOrigen, pointDest); // in
																					// meters

				search.getResults().get(i).setDistance(distance);
			}

			;

			return search;
		}

		return null;
	}

	@GET
	@Path("/{placeid}/business")
	@Produces(MediaType.APPLICATION_JSON)
	public User getBusiness(@PathParam("placeid") String placeid) {

		System.out.println("ENTRO AQUI");
		User business = UserQuery.getByPlaceId(placeid);

		return business;
	}

	@GET
	@Path("/nearbysearchfree")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseSearch nearbysearchfree(@QueryParam("lat") Double lat, @QueryParam("lng") Double lng,
			@QueryParam("radius") Long radius, @QueryParam("name") String name, @QueryParam("types") String types) {

		ClientGoogle client = new ClientGoogle();
		ResponseSearch search = client.nearBySearch(new Location(lat, lng), radius,
				name, types);
		return search;
	


	}
	
	
	@POST
	@Path("/addplace")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponsePlace addBusiness(Place place) {

		System.out.println("ENTRO AQUI");
		
		place.setAccuracy(50);
		place.setLanguage("es");
		
		System.out.println(place.getAddress());
		System.out.println(place.getLocation().getLat());
		System.out.println(place.getLocation().getLng());
		System.out.println(place.getName());




		ClientGoogle client = new ClientGoogle();
		ResponsePlace response=client.add(place);
		


		return response;
	}
}
