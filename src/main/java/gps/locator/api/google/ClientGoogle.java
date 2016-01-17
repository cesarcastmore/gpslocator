package gps.locator.api.google;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ClientGoogle {

	public static String url = "https://maps.googleapis.com/maps/api/place/";
	public static String distance = "distance";
	public static String keyword = "keyword";
	public static String name = "name";
	public static String types = "types";

	public String api_key;

	public ClientGoogle(String api_key) {
		this.api_key = api_key;
	}
	
	public ClientGoogle() {
		this.api_key = "AIzaSyCQpiQhLyTDg_uYRzW84VTBmyz3QzS2RhU";
	}

	public ResponsePlace add(Place place) {
		Client client = ClientBuilder.newClient();
		WebTarget basetarget = client.target(url);
		WebTarget addtarget = basetarget.path("add");
		WebTarget jsontarget = addtarget.path("json").queryParam("key", api_key);

		Response response = jsontarget.request(MediaType.APPLICATION_JSON).post(Entity.json(place));
		ResponsePlace responsePlace = response.readEntity(ResponsePlace.class);

		return responsePlace;

	}

	public ResponsePlace delete(String place_id) {

		Place place = new Place();
		place.setPlace_id(place_id);
		Client client = ClientBuilder.newClient();
		WebTarget basetarget = client.target(url);
		WebTarget addtarget = basetarget.path("delete");
		WebTarget jsontarget = addtarget.path("json").queryParam("key", api_key);

		Response response = jsontarget.request(MediaType.APPLICATION_JSON).post(Entity.json(place));
		ResponsePlace responsePlace = response.readEntity(ResponsePlace.class);

		return responsePlace;

	}

	public ResponseSearch nearBySearch(Location location, Long radius) {

		Client client = ClientBuilder.newClient();
		WebTarget basetarget = client.target(url);
		WebTarget addtarget = basetarget.path("nearbysearch");
		WebTarget jsontarget = addtarget.path("json").queryParam("key", api_key);

		if (location != null) {
			jsontarget = jsontarget.queryParam("location", location.getLat() + "," + location.getLng());
		}

		if (radius != null) {
			jsontarget = jsontarget.queryParam("radius", radius);
		}

		System.out.println(jsontarget.getUri().toString());

		Response response = jsontarget.request(MediaType.APPLICATION_JSON).get();
		ResponseSearch responseSearch = response.readEntity(ResponseSearch.class);

		return responseSearch;

	}

	
	
	public ResponseSearch nearBySearchByName(Location location, Long radius) {

		Client client = ClientBuilder.newClient();
		WebTarget basetarget = client.target(url);
		WebTarget addtarget = basetarget.path("nearbysearch");
		WebTarget jsontarget = addtarget.path("json").queryParam("key", api_key);

		if (location != null) {
			jsontarget = jsontarget.queryParam("location", location.getLat() + "," + location.getLng());
		}

		if (radius != null) {
			jsontarget = jsontarget.queryParam("radius", radius);
		}

	
		System.out.println(jsontarget.getUri().toString());

		Response response = jsontarget.request(MediaType.APPLICATION_JSON).get();
		ResponseSearch responseSearch = response.readEntity(ResponseSearch.class);

		return responseSearch;

	}
	
	
	public ResponseSearch nearBySearchByName(Location location, Long radius, String name) {

		Client client = ClientBuilder.newClient();
		WebTarget basetarget = client.target(url);
		WebTarget addtarget = basetarget.path("nearbysearch");
		WebTarget jsontarget = addtarget.path("json").queryParam("key", api_key);

		if (location != null) {
			jsontarget = jsontarget.queryParam("location", location.getLat() + "," + location.getLng());
		}

		if (radius != null) {
			jsontarget = jsontarget.queryParam("radius", radius);
		}

		if (name != null) {
			jsontarget = jsontarget.queryParam("name", name.replace(" ", "+"));

		}

		System.out.println(jsontarget.getUri().toString());

		Response response = jsontarget.request(MediaType.APPLICATION_JSON).get();
		ResponseSearch responseSearch = response.readEntity(ResponseSearch.class);

		return responseSearch;

	}
	
	public ResponseSearch nearBySearch(Location location, Long radius, String name,String types) {

		Client client = ClientBuilder.newClient();
		WebTarget basetarget = client.target(url);
		WebTarget addtarget = basetarget.path("nearbysearch");
		WebTarget jsontarget = addtarget.path("json").queryParam("key", api_key);

		if (location != null) {
			jsontarget = jsontarget.queryParam("location", location.getLat() + "," + location.getLng());
		}

		if (radius != null) {
			jsontarget = jsontarget.queryParam("radius", radius);
		}

		if (name != null) {
			jsontarget = jsontarget.queryParam("name", name.replace(" ", "+"));

		}
		if (types != null) {
			jsontarget = jsontarget.queryParam("types", types);

		}

		System.out.println(jsontarget.getUri().toString());

		Response response = jsontarget.request(MediaType.APPLICATION_JSON).get();
		ResponseSearch responseSearch = response.readEntity(ResponseSearch.class);

		return responseSearch;

	}

	public ResponseSearch nearBySearchByKeyWord(Location location, Long radius, String keyword) {

		Client client = ClientBuilder.newClient();
		WebTarget basetarget = client.target(url);
		WebTarget addtarget = basetarget.path("nearbysearch");
		WebTarget jsontarget = addtarget.path("json").queryParam("key", api_key);

		if (location != null) {
			jsontarget = jsontarget.queryParam("location", location.getLat() + "," + location.getLng());
		}

		if (radius != null) {
			jsontarget = jsontarget.queryParam("radius", radius);
		}

		if (name != null) {
			jsontarget = jsontarget.queryParam("keyword", keyword.replace(" ", "+"));

		}

		System.out.println(jsontarget.getUri().toString());

		Response response = jsontarget.request(MediaType.APPLICATION_JSON).get();
		ResponseSearch responseSearch = response.readEntity(ResponseSearch.class);

		return responseSearch;

	}

	public ResponseSearch nearBySearch(Location location, String rankBy) {

		Client client = ClientBuilder.newClient();
		WebTarget basetarget = client.target(url);
		WebTarget addtarget = basetarget.path("nearbysearch");
		WebTarget jsontarget = addtarget.path("json").queryParam("key", api_key);

		if (location != null) {
			jsontarget = jsontarget.queryParam("location", location.getLat() + "," + location.getLng());
		}

		if (rankBy != null) {
			jsontarget = jsontarget.queryParam("rankby", rankBy);

		}

		System.out.println(jsontarget.getUri().toString());

		Response response = jsontarget.request(MediaType.APPLICATION_JSON).get();
		ResponseSearch responseSearch = response.readEntity(ResponseSearch.class);

		return responseSearch;

	}

}
