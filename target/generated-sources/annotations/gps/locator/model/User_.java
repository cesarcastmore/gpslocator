package gps.locator.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> website;
	public static volatile SingularAttribute<User, String> lastname;
	public static volatile SingularAttribute<User, String> firstname;
	public static volatile SingularAttribute<User, String> type;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> profileimage;
	public static volatile ListAttribute<User, Request> requests;
	public static volatile SingularAttribute<User, String> username;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, Long> userId;
	public static volatile SingularAttribute<User, String> name;
	public static volatile ListAttribute<User, Permission> permissions;
	public static volatile ListAttribute<User, Address> addresses;

}

