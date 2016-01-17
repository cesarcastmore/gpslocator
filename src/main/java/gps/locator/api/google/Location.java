package gps.locator.api.google;

import javax.xml.bind.annotation.XmlRootElement;
/*
 * 
 * https://jersey.java.net/documentation/1.19/json.html
 * 
 * 
 */

public class Location {

	private double lat;
	private double lng;

	public Location() {

	}

	public Location(Double lat, Double lng) {
		this.lat = lat;
		this.lng = lng;

	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

}
