package gps.locator.api.test;

import static org.junit.Assert.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Test;

import gps.locator.api.resources.UserResource;
import gps.locator.model.User;

public class UserTester extends JerseyClient {
	
	public static String url = "http://localhost:8080/gps-locator-api/api/";

	 

 
    protected Application configure() {
        return new ResourceConfig(UserResource.class);
    }
 
    @Test
    public void test() {
    	
    	
        assertEquals("Hello World!", "HelloWorld");
    }
    
    
    public int login(){
    	
		Client client = ClientBuilder.newClient();
		WebTarget basetarget = client.target(url);
		WebTarget logintarget = basetarget.path("login");

		User userRequest= new User();
		//userRequest.setAddress("direccion 1");
		
		
		Response response= logintarget.request(MediaType.APPLICATION_JSON).post(Entity.json(userRequest));
		User userResponse=response.readEntity(User.class);
		return response.getStatus();
		
		

    	
    }
    
}