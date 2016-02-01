package gps.locator.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CategoryMenu.class)
public abstract class CategoryMenu_ {

	public static volatile SingularAttribute<CategoryMenu, Long> categoryMenuId;
	public static volatile ListAttribute<CategoryMenu, Menu> menus;
	public static volatile SingularAttribute<CategoryMenu, String> name;
	public static volatile ListAttribute<CategoryMenu, Permission> permissions;
	public static volatile SingularAttribute<CategoryMenu, String> type;

}

