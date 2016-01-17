package gps.locator.model;

import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {
	
	private String errorMessage;
	private Integer errorCode;
	private String documetation;
	
	public ErrorMessage(){
		
	}
	
	public ErrorMessage(String errorMessage, Integer errorCode, String documetation ){
		super();
		this.errorMessage= errorMessage;
		this.documetation= documetation;
		this.errorCode= errorCode;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	public String getDocumetation() {
		return documetation;
	}
	public void setDocumetation(String documetation) {
		this.documetation = documetation;
	}
	
	
	public Response toResponse(){
		Response response = Response.status(this.errorCode).entity(this).build();
		return response;
	}
	

}
