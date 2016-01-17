package gps.locator.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
@NamedQuery(name="CategoryMenu.byType", query="from CategoryMenu where type = ?")
public class CategoryMenu {

	@Id
	@GeneratedValue
	private Long categoryMenuId;
	private String name;
	@Transient
	private List<Link> links;
	private String type;

	@OneToMany(mappedBy = "categoryMenu", fetch = FetchType.LAZY)
	private List<Permission> permissions;
	

	@OneToMany(mappedBy = "categoryMenu", fetch = FetchType.EAGER)
	private List<Menu> menus;

	public CategoryMenu() {

	}
	
	public CategoryMenu(String name, String type) {
		this.name= name;
		this.type= type;

	}

	public Long getCategoryMenuId() {
		return categoryMenuId;
	}

	public void setCategoryMenuId(Long categoryMenuId) {
		this.categoryMenuId = categoryMenuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	@XmlTransient
	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
	
	public void addLink(String url, String rel){
		
		if(this.links== null){
			links  = new ArrayList<Link>();
		}
		
		Link link = new Link();
		link.setLink(url);
		link.setRel(rel);
		this.links.add(link);
		
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
		
	

}
