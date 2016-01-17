package gps.locator.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.UniqueConstraint;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@XmlRootElement
public class Menu {

	@Id
	@GeneratedValue
	private Long menuId;
	private String name;
	private String url;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryMenuId")
	private CategoryMenu categoryMenu;

	public Menu() {

	}
	
	public Menu(String name, String url,CategoryMenu categoryMenu ){
		this.name=name;
		this.url=url;
		this.categoryMenu= categoryMenu;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@XmlTransient
	public CategoryMenu getCategoryMenu() {
		return categoryMenu;
	}

	public void setCategoryMenu(CategoryMenu categoryMenu) {
		this.categoryMenu = categoryMenu;
	}

}
