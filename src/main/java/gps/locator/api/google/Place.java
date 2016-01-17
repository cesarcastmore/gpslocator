package gps.locator.api.google;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Place {

	private String place_id;
	private int accuracy;
	private String name;
	private String phone_number;
	private String address;
	private String[] types;;
	private String website;
	private String language;
	private Location location;
	
	public Place(){
		
	}
	

	public int getAccuracy() {
		return accuracy;
	}


	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String[] getTypes() {
		return types;
	}


	public void setTypes(String[] types) {
		this.types = types;
	}


	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}


	public String getPlace_id() {
		return place_id;
	}


	public void setPlace_id(String place_id) {
		this.place_id = place_id;
	}
	
	
	
}
