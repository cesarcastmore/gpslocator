package gps.locator.model;

import java.security.Principal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "UserDetails")
@NamedQueries({ @NamedQuery(name = "User.byId", query = "from User where userId = ?"),
		@NamedQuery(name = "User.checkuser", query = "from User where username = ? and password= ?"),
		@NamedQuery(name = "User.byPlaceId", query = "from User where place_id = ?")

})
@XmlRootElement
public class User implements Principal {

	@Id
	@GeneratedValue
	private Long userId;
	private String name;
	private String firstname;
	private String lastname;
	@Column(name = "username", unique = true)
	private String username;
	private String password;
	@Column(name = "email", unique = true)
	private String email;
	private String type;
	private String website;
	private String profileimage="images/user.jpg";

	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Request> requests;
	

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Permission> permissions;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<Address> addresses;
	
	@OneToMany(mappedBy = "user", fetch= FetchType.EAGER)
	private List<UserService> userServices;

	public User() {

	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}


	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	@XmlTransient
	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public List<Request> getRequests() {
		return requests;
	}

	@XmlTransient
	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	@XmlTransient
	public List<UserService> getUserServices() {
		return userServices;
	}

	public void setUserServices(List<UserService> userServices) {
		this.userServices = userServices;
	}

	public String getProfileimage() {
		return profileimage;
	}

	public void setProfileimage(String profileimage) {
		this.profileimage = profileimage;
	}
	
	
	

	
	

}
