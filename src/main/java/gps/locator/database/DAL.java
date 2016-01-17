package gps.locator.database;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DAL {

	public Session session;
	public Query query;
	public Criteria criteria;
	public SQLQuery sqlQuery;

	public DAL() {
		session = null;
		query = null;
		criteria = null;
		sqlQuery = null;

	}

	public void openSession() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
	}

	public void save(Object object) {
		session.save(object);

	}

	public Object load(Class clas, Serializable id) {

		Object obj = session.load(clas, id);
		return obj;
	}

	public Object get(Class clas, Serializable id) {

		Object obj = session.get(clas, id);
		return obj;
	}

	public void update(Object obj) {
		session.update(obj);
	}
	
	public void refresh(Object obj) {
		session.refresh(obj);
	}

	public void delele(Object obj) {
		session.delete(obj);
	}

	public void createNamedQuery(String namedQuery) {
		query = session.getNamedQuery(namedQuery);
	}

	public void createSQLQuery(String sql) {
		sqlQuery = session.createSQLQuery(sql);
	}

	public void addEntity(Class clas) {
		sqlQuery.addEntity(clas);
	}

	public void setString(int no, String value) {
		if (query != null) {
			query.setString(no, value);
		} else if (sqlQuery != null) {
			sqlQuery.setString(no, value);
		}
	}

	public void setString(String key, String value) {
		if (query != null) {
			query.setString(key, value);
		} else if (sqlQuery != null) {
			sqlQuery.setString(key, value);
		}
	}

	public void setLong(int no, Long value) {
		if (query != null) {
			query.setLong(no, value);
			
		} else if (sqlQuery != null) {
			sqlQuery.setLong(no, value);
		}
	}
	
	
	public void setEntity(int no, Object obj) {
		if (query != null) {
			query.setEntity(no, obj);
		}
	}

	public void setLong(String key, Long value) {
		if (query != null) {
			query.setLong(key, value);
		} else if (sqlQuery != null) {
			sqlQuery.setLong(key, value);
		}
	}

	public void setDouble(int no, Double value) {
		if (query != null) {
			query.setDouble(no, value);
		} else if (sqlQuery != null) {
			sqlQuery.setDouble(no, value);
		}
	}

	public void setDouble(String key, Double value) {
		if (query != null) {
			query.setDouble(key, value);
		} else if (sqlQuery != null) {
			sqlQuery.setDouble(key, value);
		}
	}

	public List list() {
		if (query != null)
			return query.list();
		else if (criteria != null) {
			return criteria.list();
		} else if (sqlQuery != null) {
			return sqlQuery.list();

		}

		return null;
	}

	public void createCriteria(Class cla) {
		criteria = session.createCriteria(cla);
	}

	public void add(Criterion ctn) {
		criteria.add(ctn);
	}

	public void closeSession() {
		session.getTransaction().commit();
		session.close();
	}

}
