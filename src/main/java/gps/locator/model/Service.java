package gps.locator.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**************************************************
 * 
 * @author ccastillo
 *
 *         Explicacion de @manytomany
 *         http://outbottle.com/java-hibernate-manytomany-tutorial-with-add-and-
 *         delete-examples/
 *
 *
 *
 */

@Entity
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Service.all", query = "from Service"),
		@NamedQuery(name = "Service.byName", query = "from Service where name = ?"), })
public class Service {

	@Id
	@GeneratedValue
	private Long serviceId;
	private String name;
	private String description;

	@OneToMany(mappedBy = "service", fetch = FetchType.LAZY)
	private List<Request> request;

	@OneToMany(mappedBy = "service", fetch = FetchType.LAZY)
	private List<UserService> userServices;

	public Service() {

	}
	
	public Service(String name, String description) {
		this.name= name;
		this.description= description;

	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	@XmlTransient
	public List<UserService> getUserServices() {
		return userServices;
	}

	public void setUserServices(List<UserService> userServices) {
		this.userServices = userServices;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@XmlTransient
	public List<Request> getRequest() {
		return request;
	}

	public void setRequest(List<Request> request) {
		this.request = request;
	}



}
