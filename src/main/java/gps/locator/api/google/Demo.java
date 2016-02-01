package gps.locator.api.google;

public class Demo {
	
	public static void main(String args[]){
		ClientGoogle client = new ClientGoogle();
		ResponsePlace response=client.delete("qgYvCi0wMDAwMDAwMzlkNDQwYTZjOjg2NjI5NmRmY2ZiOmEyOWU2OGQ2YWM0MWVkYTY");
		System.out.println(response.getStatus());
		
		
	}

}
