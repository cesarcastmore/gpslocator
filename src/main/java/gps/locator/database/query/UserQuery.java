package gps.locator.database.query;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;

import gps.locator.database.DAL;
import gps.locator.model.User;

public class UserQuery {
	
	public static User getByRequestId(Long RequestId){
		DAL db = new DAL();
		db.openSession();
		db.createSQLQuery("SELECT * FROM UserDetails u INNER JOIN Request r ON r.userId = u.userId WHERE r.requestId= ?");
		db.setLong(0, RequestId);
		db.addEntity(User.class);
		
		List<User> users = db.list();		
		
		if(users.isEmpty()){
			return null;
		}
		
		db.closeSession();
		
		User user = users.get(0);
		
		//Protegiendo los datos 
		user.setPassword(null);
		user.setUsername(null);
		return user;

	}
	
	
	public static User getByPlaceId(String placeId){
		DAL db = new DAL();
		db.openSession();
		db.createSQLQuery("SELECT * FROM UserDetails u  INNER JOIN Address a ON a.userId= u.userId WHERE a.place_id = ?");
		db.setString(0, placeId);
		db.addEntity(User.class);
		
		List<User> users = db.list();		
		
		if(users.isEmpty()){
			return null;
		}
		
		db.closeSession();
		
		User user = users.get(0);
		
		//Protegiendo los datos 
		user.setPassword(null);
		user.setUsername(null);
		return user;

	}

}
