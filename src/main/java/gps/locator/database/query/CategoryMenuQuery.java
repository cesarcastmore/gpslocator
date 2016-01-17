package gps.locator.database.query;

import java.util.List;

import gps.locator.database.DAL;
import gps.locator.model.CategoryMenu;

public class CategoryMenuQuery {
	
	public static List<CategoryMenu> getCategoryMenyByType(String type){
		DAL dal = new DAL();
		dal.openSession();
		dal.createNamedQuery("CategoryMenu.byType");
		dal.setString(0, type);
		List<CategoryMenu> categories = dal.list();
		dal.closeSession();
		
		return categories;
	}

}
