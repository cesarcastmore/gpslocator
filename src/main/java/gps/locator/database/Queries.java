package gps.locator.database;

import java.util.List;




import gps.locator.model.CategoryMenu;
import gps.locator.model.User;

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
