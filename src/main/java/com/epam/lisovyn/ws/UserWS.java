package com.epam.lisovyn.ws;

import com.epam.lisovyn.exceptions.StorageNotFoundException;
import com.epam.lisovyn.model.UserModel;
import com.epam.lisovyn.storage.ModelStorage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.jboss.resteasy.logging.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.*;

@Path("/user")
@Api(value = "/user", description = "Operations about users")
public class UserWS {

	private static final Logger LOGGER = Logger.getLogger(UserWS.class);

	private ModelStorage<UserModel> userStorage = new ModelStorage<UserModel>();

	public UserWS() {
		try {
			initStorage();
		} catch (StorageNotFoundException e) {
			LOGGER.warn("Cannot load initial data");
		}
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(produces = MediaType.APPLICATION_JSON, value = "Return the User object by its id")
	public Response getUser(@ApiParam(value = "id of the user", required = true) @PathParam("id") int id) {
		UserModel user;
		try {
			user = userStorage.get(id);
			return Response.status(OK).entity(user).build();
		} catch (StorageNotFoundException e) {
			return Response.status(NOT_FOUND).build();
		}
	}

	@POST
	@Path("/{id}/{name}/{secondName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUser(@PathParam("id") int id, @PathParam("name") String name, @PathParam("secondName") String secName)  {
		UserModel user = new UserModel(id, name, secName);
		try {
			userStorage.put(user);
			return Response.status(CREATED).entity(user).build();
		} catch (StorageNotFoundException e) {
			return Response.status(NOT_FOUND).build();
		}
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(@PathParam("id") int id, @QueryParam("name") String name, @QueryParam("secName") String secName)  {
		UserModel user = new UserModel(id, name, secName);
		try {
			user = userStorage.update(user);
			return Response.status(OK).entity(user).build();
		} catch (StorageNotFoundException e) {
			return Response.status(NOT_FOUND).build();
		}
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUser(@PathParam("id") int id)  {
		try {
			userStorage.delete(id);
			return Response.status(OK).entity("").build();
		} catch (StorageNotFoundException e) {
			return Response.status(NOT_FOUND).build();
		}
	}

	private void initStorage() throws StorageNotFoundException {
		userStorage.put(new UserModel(100, "Alex", "Jonsi"));
		userStorage.put(new UserModel(101, "John", "Broslovski"));
		userStorage.put(new UserModel(102, "Artur", "Girr"));
		userStorage.put(new UserModel(103, "Ben", "Tallak"));
		userStorage.put(new UserModel(104, "Kingsly", "Okosun"));
		userStorage.put(new UserModel(105, "Albert", "Delacoka"));
		userStorage.put(new UserModel(106, "Bill", "Freesby"));
		userStorage.put(new UserModel(107, "Andrew", "Brosnan"));
	}

}
