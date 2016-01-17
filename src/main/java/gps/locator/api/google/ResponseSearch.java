package gps.locator.api.google;

import java.util.ArrayList;
import java.util.List;

public class ResponseSearch {
	private List<Result> results= new ArrayList<Result>();
	private String status;
	
	private ResponseSearch(){
		
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
