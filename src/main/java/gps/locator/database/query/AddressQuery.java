package gps.locator.database.query;

import java.util.List;

import gps.locator.database.DAL;
import gps.locator.model.Address;
import gps.locator.model.User;

public class AddressQuery {
	
	public static Address getAddressByUser(User user){
		DAL db= new DAL();
		db.openSession();
		
		db.createNamedQuery("Address.byUser");
		db.setEntity(0, user);
		List<Address> addresses = db.list();
				
		
		if(addresses == null){
			db.closeSession();
			return null;
		}
		
		db.closeSession();
		
		return addresses.get(0);

	}

}
