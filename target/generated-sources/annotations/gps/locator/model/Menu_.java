package gps.locator.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Menu.class)
public abstract class Menu_ {

	public static volatile SingularAttribute<Menu, String> name;
	public static volatile SingularAttribute<Menu, CategoryMenu> categoryMenu;
	public static volatile SingularAttribute<Menu, Long> menuId;
	public static volatile SingularAttribute<Menu, String> url;

}

