package gps.locator.api.resources;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

/*
 * 
 * DOCUMENTACION DE MULTI_PART_FORM
 * 
 * 
 * https://jersey.java.net/documentation/latest/media.html#multipart
 * http://stackoverflow.com/questions/18252990/uploading-file-using-jersey-over-restfull-service-and-the-resource-configuration
 * http://stackoverflow.com/questions/25797650/fileupload-with-jaxrs 
 * no tomar los @ParaForm de la ultima referencia
 * 
 */

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import gps.locator.database.DAL;
import gps.locator.model.User;

@Path("upload")
public class UploadResource {

	@Context
	private SecurityContext securityContext;

	@POST
	@Path("/profile/image") // Your Path or URL to call this service
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String uploadFile(@DefaultValue("true") @FormDataParam("enabled") boolean enabled,
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail, @Context UriInfo urlInfo) {

		// Your local disk path where you want to store the file
		User user = (User) securityContext.getUserPrincipal();
				
		String username = user.getUsername();
		String path = "/home/ccastillo/workspace/gps-locator-api/src/main/webapp/images/" + username+ "/";
		createDir(path);
		String uploadedFileLocation = path + fileDetail.getFileName();		
		
		String url= urlInfo.getBaseUri().toString().replaceAll("api/", "images/"+ username +"/"+ fileDetail.getFileName());
		user.setProfileimage(url);


		System.out.println(uploadedFileLocation);
		
		// save it
		File objFile = new File(uploadedFileLocation);
		if (objFile.exists()) {
			objFile.delete();

		}


		DAL db = new DAL();
		db.openSession();
		db.update(user);
		db.closeSession();

		saveToFile(uploadedInputStream, uploadedFileLocation);

		String output = "File uploaded via Jersey based RESTFul Webservice to: " + uploadedFileLocation;

		return url;
	}

	private void saveToFile(InputStream uploadedInputStream, String uploadedFileLocation) {

		try {
			OutputStream out = null;
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	
	private void  createDir(String path){
		File carpeta = new File(path);
		if(!carpeta.exists()){
			carpeta.mkdirs();
		}
		
		
	}

}
