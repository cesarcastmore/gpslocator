package gps.locator.api.resources;



import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

@ApplicationPath("api")
public class ApplicationAPI extends Application {
	
	
	  public Set<Class<?>> getClasses() {
	        final Set<Class<?>> resources = new HashSet<Class<?>>();

	        // Add your resources.
	        resources.add(UploadResource.class);
	        resources.add(AddressResource.class);
	        resources.add(BusinessResource.class);
	        resources.add(CategoryMenuResource.class);
	        resources.add(CredentialResource.class);
	        resources.add(RequestResource.class);
	        resources.add(SecurityFilter.class);
	        resources.add(UserResource.class);
	        resources.add(PlaceResource.class);





	        // Add additional features such as support for Multipart.
	        resources.add(MultiPartFeature.class);
	        resources.add(LoggingFilter.class);

	        return resources;
	    }
	
}