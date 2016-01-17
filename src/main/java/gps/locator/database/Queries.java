package gps.locator.database;

import java.util.List;



import org.hibernate.criterion.Restrictions;

import gps.locator.calc.Coordinate;
import gps.locator.calc.DegreeCoordinate;
import gps.locator.calc.EarthCalc;
import gps.locator.calc.Point;
import gps.locator.model.Address;
import gps.locator.model.Service;
import gps.locator.model.CategoryMenu;
import gps.locator.model.User;
import gps.locator.model.Request;

public class Queries {

	public static User getUser(String username, String password) {
		DAL db = new DAL();
		db.openSession();
		db.createNamedQuery("User.checkuser");

		db.setString(0, username);
		db.setString(1, password);

		List<User> users = db.list();

		if (users.isEmpty()) {
			return null;
		}

		User user = users.get(0);
		db.closeSession();

		return user;

	}



	public static List<CategoryMenu> getCategoryMenuByUser(User user) {
		DAL db = new DAL();
		db.openSession();

		db.createSQLQuery(
				"SELECT * FROM CategoryMenu cm INNER JOIN Permission p ON cm.categoryMenuId = p.categoryMenuId  WHERE p.userId = ?  ");
		db.setLong(0, user.getUserId());
		db.addEntity(CategoryMenu.class);

		List<CategoryMenu> categoryMenus = db.list();
		db.closeSession();

		return categoryMenus;

	}

	
	
	
}
