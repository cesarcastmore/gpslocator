package gps.locator.api.resources;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.Priorities;

import org.glassfish.jersey.internal.util.Base64;

import gps.locator.database.Queries;
import gps.locator.model.User;

/*
 * 
 * 
 * http://www.nextinstruction.com/custom-jersey-security-filter.html
 * 
 */

@Provider
@Priority(Priorities.AUTHORIZATION)
public class SecurityFilter implements ContainerRequestFilter {

	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic";

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		String url = requestContext.getUriInfo().getPath();
		boolean notAllow = true;
		if (url.contains("credentials")) {
			notAllow = false;
		} else if (url.contains("place/nearbysearchfree")) {
			notAllow = false;
		} else if (url.contains("services")) {
			notAllow = false;
		}

		if (notAllow) {
			List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);

			if (authHeader != null && authHeader.size() > 0) {
				String authToken = authHeader.get(0);
				authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "").trim();
				String decodeString = Base64.decodeAsString(authToken);
				StringTokenizer tokenizer = new StringTokenizer(decodeString, ":");
				String username = tokenizer.nextToken();
				String password = tokenizer.nextToken();

				System.out
						.println("entroooo a validar esta informacion" + "username" + username + "password" + password);

				final User user = Queries.getUser(username, password);

				if (user != null) {

					requestContext.setSecurityContext(new SecurityContext() {
						@Override
						public Principal getUserPrincipal() {
							return user;
						}

						@Override
						public boolean isUserInRole(String role) {
							return user != null;
						}

						@Override
						public boolean isSecure() {
							return user != null;
						}

						@Override
						public String getAuthenticationScheme() {
							return "Basic";
						}
					});

					return;
				}
			}

			Response unauthorizedstatus = Response.status(Response.Status.UNAUTHORIZED)
					.entity("The user cannot access the resource").build();

			System.out.println("ABORTAR");

			requestContext.abortWith(unauthorizedstatus);

		}

	}

}
