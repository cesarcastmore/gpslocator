package gps.locator.database.query;

import java.util.List;

import gps.locator.database.DAL;
import gps.locator.model.Service;
import gps.locator.model.User;

public class ServiceQuery {
	public static List<Service> getServices(){
		DAL dal = new DAL();
		dal.openSession();
		dal.createNamedQuery("Service.all");
		List<Service> services = dal.list();
		dal.closeSession();
		
		return services;
	}
	
	public static Service getServices(String name){
		DAL dal = new DAL();
		dal.openSession();
		dal.createNamedQuery("Service.byName");
		dal.setString(0, name);
		List<Service> services = dal.list();
		dal.closeSession();
		
		return services.get(0);
	}
	
	
	public static List<Service> getServices(User user){
		DAL dal = new DAL();
		dal.openSession();
		dal.createSQLQuery("SELECT * FROM Service s INNER JOIN UserService us ON us.serviceId = s.serviceId WHERE userId=?");
		dal.setLong(0, user.getUserId());
		dal.addEntity(Service.class);

		List<Service> services = dal.list();
		dal.closeSession();
		
		return services;
	}
	

}
