package gps.locator.database.query;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import gps.locator.database.DAL;
import gps.locator.model.Tree;

public class TreeQuery {
	
	public static Tree getTree(Long nodeId){
		DAL dal = new DAL();
		dal.openSession();
		
		dal.createCriteria(Tree.class);
		dal.add(Restrictions.eq("nodeId", nodeId));

		List<Tree> trees = dal.list();

		dal.closeSession();
		
		return trees.get(0);

		
	}

}
