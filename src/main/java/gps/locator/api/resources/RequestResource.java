package gps.locator.api.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import gps.locator.database.DAL;
import gps.locator.database.Queries;
import gps.locator.database.query.AddressQuery;
import gps.locator.database.query.RequestQuery;
import gps.locator.database.query.TreeQuery;
import gps.locator.database.query.UserQuery;
import gps.locator.model.Address;
import gps.locator.model.ErrorMessage;
import gps.locator.model.Request;
import gps.locator.model.Tree;
import gps.locator.model.User;

@Path("requests")
public class RequestResource {

	public RequestResource() {
		System.out.println("ENTRO AQUI");

	}

	@Context
	private SecurityContext securityContext;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Request> getRequests(@QueryParam("service") String categoryname, @QueryParam("findBy") String findBy) {

		System.out.println("ENTRO AQUI");

		User user = (User) securityContext.getUserPrincipal();
		if (user.getType().equals("client")) {
			if (categoryname != null) {
				if (categoryname.equals("all")) {
					System.out.println("HELLO I am at Home");
					return RequestQuery.getRequestsByClient(user);
				} else {
					return RequestQuery.getRequestsByClientAndService(user, categoryname);
				}
			} else if (findBy != null) {
				return RequestQuery.findRequestsByClient(user, findBy);
			} else {
				return new ArrayList<Request>();
			}

		} else {
			if (findBy != null) {
				return RequestQuery.findRequestsByBusiness(user, findBy);
			} else if (categoryname != null) {
				return RequestQuery.getRequestsByBusiness(user);
			} else {
				return new ArrayList<Request>();

			}
		}

	}

	@GET
	@Path("/{requestId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Request getRequest(@PathParam("requestId") Long requestId) {

		DAL db = new DAL();
		db.openSession();

		Request req = (Request) db.get(Request.class, requestId);
		db.closeSession();

		return req;

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public Request createRequest(Request req) {

		DAL db = new DAL();
		db.openSession();

		User user = (User) securityContext.getUserPrincipal();

		if (req.getLatitude() == null && req.getLongitude() == null) {
			if (user.getAddresses().isEmpty()) {
				throw new WebApplicationException(
						new ErrorMessage("The address is missing", 404, "The address is missing").toResponse());
			} else {
				Address address = user.getAddresses().get(0);

				req.setLatitude(address.getLatitude());
				req.setLongitude(address.getLongitude());
			}

		}

		req.setUser(user);
		db.save(req);
		db.closeSession();

		return req;

	}

	@PUT
	@Path("/{requestId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Request updateRequest(Request req, @PathParam("requestId") Long requestId) {

		DAL db = new DAL();
		db.openSession();

		User user = (User) securityContext.getUserPrincipal();
		req.setUser(user);

		req.setRequestId(requestId);

		db.update(req);
		db.closeSession();

		return req;

	}

	@DELETE
	@Path("/{requestId}")
	public Request updateRequest(@PathParam("requestId") Long requestId) {

		DAL db = new DAL();
		db.openSession();

		Request req = (Request) db.get(Request.class, requestId);

		db.delele(req);

		db.closeSession();

		return req;

	}

	@GET
	@Path("/{requestId}/responses")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Request> getResponses(@PathParam("requestId") Long requestId) {
		System.out.println("ENTRO AL GET");

		return RequestQuery.getNodes(requestId);
	}

	@GET
	@Path("/{requestId}/offers")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Request> getOffers(@PathParam("requestId") Long requestId) {
		User user = (User) securityContext.getUserPrincipal();

		if (user.getType().equals("business")) {
			return RequestQuery.getNodes(requestId, user);
		} else {
			return RequestQuery.getNodes(requestId);
		}

	}

	@GET
	@Path("/{requestId}/nodes/{nodeId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Request getNode(@PathParam("requestId") Long requestId, @PathParam("nodeId") Long nodeId) {

		System.out.println("ENTRO AL GET VARIOS");

		DAL db = new DAL();
		db.openSession();

		Request request = (Request) db.get(Request.class, nodeId);
		db.closeSession();

		return request;
	}

	@GET
	@Path("/{requestId}/author")
	@Produces(MediaType.APPLICATION_JSON)
	public User getAuthor(@PathParam("requestId") Long requestId) {

		User author = UserQuery.getByRequestId(requestId);

		return author;
	}
	
	
	@GET
	@Path("/{requestId}/author/address")
	@Produces(MediaType.APPLICATION_JSON)
	public Address getAddress(@PathParam("requestId") Long requestId) {

		User author = UserQuery.getByRequestId(requestId);
		Address address = AddressQuery.getAddressByUser(author);

		return address;
	}

	@POST
	@Path("/{requestId}/nodes")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Request createNode(@PathParam("requestId") Long requestId, Request node) {

		User user = (User) securityContext.getUserPrincipal();

		System.out.print("ENTRO A POST ");
		saveMessage(user, requestId, node);

		return node;
	}

	@DELETE
	@Path("/{requestId}/nodes/{nodeId}")
	public void deleteNode(@PathParam("requestId") Long requestId, @PathParam("nodeId") Long nodeId) {

		deleteNode(nodeId);

	}

	@PUT
	@Path("/{requestId}/nodes/{nodeId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Request createNode(@PathParam("requestId") Long requestId, @PathParam("nodeId") Long nodeId, Request node) {
		return new Request();
	}

	public Request saveMessage(User user, Long fatherId, Request request) {
		DAL db = new DAL();
		db.openSession();

		if(request.getLatitude()== null && request.getLongitude()== null && !user.getAddresses().isEmpty()){
			Address address = user.getAddresses().get(0);
			request.setLatitude(address.getLatitude());
			request.setLongitude(address.getLongitude());
		
		}
		
		request.setUser(user);

		db.save(request);
		db.closeSession();

		db.openSession();
		db.refresh(request);

		Tree tree = new Tree();
		tree.setParentId(fatherId);
		tree.setNodeId(request.getRequestId());
		db.save(tree);
		db.closeSession();

		return request;

	}

	public void deleteNode(Long nodeId) {
		Tree tree = TreeQuery.getTree(nodeId);

		DAL db = new DAL();
		db.openSession();

		Request request = new Request();
		request.setRequestId(nodeId);

		db.delele(tree);
		db.delele(request);

		db.closeSession();

	}

}
