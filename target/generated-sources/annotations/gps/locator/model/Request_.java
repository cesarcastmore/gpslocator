package gps.locator.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Request.class)
public abstract class Request_ {

	public static volatile SingularAttribute<Request, Date> created;
	public static volatile SingularAttribute<Request, String> commit;
	public static volatile SingularAttribute<Request, Long> requestId;
	public static volatile SingularAttribute<Request, Long> likes;
	public static volatile SingularAttribute<Request, String> categoryname;
	public static volatile SingularAttribute<Request, Double> longitude;
	public static volatile SingularAttribute<Request, Double> latitude;
	public static volatile SingularAttribute<Request, Date> toDate;
	public static volatile SingularAttribute<Request, User> user;

}

