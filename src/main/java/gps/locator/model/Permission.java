package gps.locator.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Permission {

	@Id
	@GeneratedValue
	private Long permissionId;
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryMenuId")
	private CategoryMenu categoryMenu;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;

	
	public Permission() {

	}

	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public CategoryMenu getCategoryMenu() {
		return categoryMenu;
	}

	public void setCategoryMenu(CategoryMenu categoryMenu) {
		this.categoryMenu = categoryMenu;
	}
	
	

}
