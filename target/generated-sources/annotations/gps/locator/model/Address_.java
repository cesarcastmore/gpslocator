package gps.locator.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Address.class)
public abstract class Address_ {

	public static volatile SingularAttribute<Address, String> place_id;
	public static volatile SingularAttribute<Address, String> phone;
	public static volatile SingularAttribute<Address, String> outdoorNumber;
	public static volatile SingularAttribute<Address, Integer> zoom;
	public static volatile SingularAttribute<Address, String> street;
	public static volatile SingularAttribute<Address, String> neighborhood;
	public static volatile SingularAttribute<Address, String> state;
	public static volatile SingularAttribute<Address, Long> addressId;
	public static volatile SingularAttribute<Address, String> city;
	public static volatile SingularAttribute<Address, String> postalCode;
	public static volatile SingularAttribute<Address, String> categoryname;
	public static volatile SingularAttribute<Address, String> indoorNumber;
	public static volatile SingularAttribute<Address, Double> longitude;
	public static volatile SingularAttribute<Address, Long> radius;
	public static volatile SingularAttribute<Address, Double> latitude;
	public static volatile SingularAttribute<Address, User> user;

}

