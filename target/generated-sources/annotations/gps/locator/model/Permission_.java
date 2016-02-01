package gps.locator.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Permission.class)
public abstract class Permission_ {

	public static volatile SingularAttribute<Permission, String> name;
	public static volatile SingularAttribute<Permission, CategoryMenu> categoryMenu;
	public static volatile SingularAttribute<Permission, Long> permissionId;
	public static volatile SingularAttribute<Permission, User> user;

}

