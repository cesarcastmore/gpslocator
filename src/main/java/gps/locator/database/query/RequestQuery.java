package gps.locator.database.query;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import gps.locator.calc.Coordinate;
import gps.locator.calc.DegreeCoordinate;
import gps.locator.calc.EarthCalc;
import gps.locator.calc.Point;
import gps.locator.database.DAL;
import gps.locator.model.Address;
import gps.locator.model.Request;
import gps.locator.model.Service;
import gps.locator.model.User;

public class RequestQuery {
	
	//Es un metodo para obtener todas las distancias de las solicitudes
		public static List<Request> findRequestsByBusiness(User business, String findby) {
			DAL db = new DAL();
			db.openSession();
			
			db.createCriteria(Request.class);
			db.add(Restrictions.like("commit", "%"+findby+"%"));
			db.add(Restrictions.isNotNull("service"));


			List<Request> requests = db.list();


			if (requests.isEmpty()) {
				return null;
			}
			
			List<Address> addresses  = business.getAddresses();
			if(addresses.isEmpty()){
				return null;
			}
			Address address = addresses.get(0);

			
			for(int i=0; i< requests.size(); i++ ){
				
				Coordinate lat = new DegreeCoordinate(requests.get(i).getLatitude());
				Coordinate lng = new DegreeCoordinate(requests.get(i).getLongitude());
				Point kew = new Point(lat, lng);

				//Richmond, London
				lat = new DegreeCoordinate(address.getLatitude());
				lng = new DegreeCoordinate(address.getLongitude());
				Point richmond = new Point(lat, lng);

				double distance = EarthCalc.getDistance(richmond, kew); //in meters
				requests.get(i).setDistance(distance);
				
			}

			db.closeSession();

			return requests;

		}

	//Es un metodo para obtener todas las distancias de las solicitudes
	public static List<Request> getRequestsByBusiness(User business, Service service) {
		DAL db = new DAL();
		db.openSession();
		
		db.createCriteria(Request.class);
		db.add(Restrictions.eq("service", service));

		List<Request> requests = db.list();


		if (requests.isEmpty()) {
			return null;
		}
		
		List<Address> addresses  = business.getAddresses();
		if(addresses.isEmpty()){
			return null;
		}
		Address address = addresses.get(0);

		
		for(int i=0; i< requests.size(); i++ ){
			
			Coordinate lat = new DegreeCoordinate(requests.get(i).getLatitude());
			Coordinate lng = new DegreeCoordinate(requests.get(i).getLongitude());
			Point kew = new Point(lat, lng);

			//Richmond, London
			lat = new DegreeCoordinate(address.getLatitude());
			lng = new DegreeCoordinate(address.getLongitude());
			Point richmond = new Point(lat, lng);

			double distance = EarthCalc.getDistance(richmond, kew); //in meters
			requests.get(i).setDistance(distance);
			
		}

		db.closeSession();

		return requests;

	}

	
	public static List<Request> getRequestsByBusiness(User business, List<Service> services) {
		DAL db = new DAL();
		db.openSession();
		
		db.createCriteria(Request.class);
		db.add(Restrictions.in("service", services.toArray()));

		List<Request> requests = db.list();


		if (requests.isEmpty()) {
			return null;
		}
		
		List<Address> addresses  = business.getAddresses();
		if(addresses.isEmpty()){
			return null;
		}
		Address address = addresses.get(0);

		
		for(int i=0; i< requests.size(); i++ ){
			
			Coordinate lat = new DegreeCoordinate(requests.get(i).getLatitude());
			Coordinate lng = new DegreeCoordinate(requests.get(i).getLongitude());
			Point kew = new Point(lat, lng);

			//Richmond, London
			lat = new DegreeCoordinate(address.getLatitude());
			lng = new DegreeCoordinate(address.getLongitude());
			Point richmond = new Point(lat, lng);

			double distance = EarthCalc.getDistance(richmond, kew); //in meters
			requests.get(i).setDistance(distance);
			
		}

		db.closeSession();

		return requests;

	}
	
	public static List<Request> getRequestsByClient(User user) {
		DAL db = new DAL();
		db.openSession();

		db.createCriteria(Request.class);
		db.add(Restrictions.eq("user", user));
		db.add(Restrictions.isNotNull("service"));


		List<Request> requests = db.list();

		if (requests.isEmpty()) {
			return null;
		}

		db.closeSession();

		return requests;

	}
	
	
	
	public static List<Request> getRequestsByClientAndService(User user, String name) {
		DAL db = new DAL();
		db.openSession();
		
		db.createNamedQuery("Request.byServiceName");
		db.setString(0, name);
		
		List<Request> requests = db.list();
		
		for(Request req: requests){
			System.out.println("EL VALOR DEL SERVICIO ES"+  req.getCommit());

		}

		if (requests.isEmpty()) {
			System.out.println("entroo aqui a null ");
			return null;
		}

		db.closeSession();

		return requests;

	}
	
	public static List<Request> findRequestsByClient(User user, String findby) {
		DAL db = new DAL();
		db.openSession();
		
		db.createCriteria(Request.class);
		db.add(Restrictions.like("commit", "%"+findby+"%"));
		List<Request> requests = db.list();

		if (requests.isEmpty()) {
			System.out.println("entroo aqui a null ");
			return null;
		}

		db.closeSession();

		return requests;

	}
	
	public static List<Request> getNodes(Long requestId) {

		DAL db = new DAL();
		db.openSession();

		db.createSQLQuery("SELECT * FROM Request WHERE requestId IN (SELECT nodeId FROM Tree t WHERE t.parentId = ? )");
		db.setLong(0, requestId);
		db.addEntity(Request.class);
		List<Request> requests = db.list();

		return requests;

	}
	
	public static List<Request> getNodes(Long requestId, User user) {

		DAL db = new DAL();
		db.openSession();

		db.createSQLQuery("SELECT * FROM Request r WHERE r.requestId IN (SELECT nodeId FROM Tree t WHERE t.parentId = ?  AND r.userId = ? )");
		db.setLong(0, requestId);
		db.setLong(1, user.getUserId());
		db.addEntity(Request.class);
		List<Request> requests = db.list();

		return requests;

	}
}
