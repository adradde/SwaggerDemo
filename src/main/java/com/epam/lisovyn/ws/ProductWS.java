package com.epam.lisovyn.ws;

import com.epam.lisovyn.exceptions.StorageNotFoundException;
import com.epam.lisovyn.model.ProductModel;
import com.epam.lisovyn.storage.ModelStorage;
import io.swagger.annotations.ApiParam;
import org.jboss.resteasy.logging.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.*;

/**
 * Created by Andrii_Lisovyn on 15.4.2016.
 */
//@Path("/product")
public class ProductWS {

	private ModelStorage<ProductModel> productStorage = new ModelStorage<ProductModel>();


	private static final Logger LOGGER = Logger.getLogger(ProductWS.class);

	public ProductWS() {
		try {
			initStorage();
		} catch (StorageNotFoundException e) {
			LOGGER.warn("Cannot load initial data");
		}
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProduct(@ApiParam(value = "id of the user", required = true) @PathParam("id") int id) {
		ProductModel user;
		try {
			user = productStorage.get(id);
			return Response.status(OK).entity(user).build();
		} catch (StorageNotFoundException e) {
			return Response.status(NOT_FOUND).build();
		}
	}

	@POST
	@Path("/{id}/{article}/{price}/{color}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createProduct(@PathParam("id") int id, @PathParam("article") String article, @PathParam("price") double price, @PathParam("color") String color) {
		ProductModel user = new ProductModel(id, article, price, color);
		try {
			productStorage.put(user);
			return Response.status(CREATED).entity(user).build();
		} catch (StorageNotFoundException e) {
			return Response.status(NOT_FOUND).build();
		}
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateProduct(@PathParam("id") int id, @QueryParam("article") String article, @QueryParam("price") double price, @QueryParam("color") String color) {
		ProductModel user = new ProductModel(id, article, price, color);
		try {
			user = productStorage.update(user);
			return Response.status(OK).entity(user).build();
		} catch (StorageNotFoundException e) {
			return Response.status(NOT_FOUND).build();
		}
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteProduct(@PathParam("id") int id) {
		try {
			productStorage.delete(id);
			return Response.status(OK).entity("").build();
		} catch (StorageNotFoundException e) {
			return Response.status(NOT_FOUND).build();
		}
	}

	private void initStorage() throws StorageNotFoundException {
		productStorage.put(new ProductModel(100, "Car", 		100., 	"Red"));
		productStorage.put(new ProductModel(101, "Telephone", 	200., 	"Black"));
		productStorage.put(new ProductModel(102, "Computer", 	150., 	"Grey"));
		productStorage.put(new ProductModel(103, "Table", 		50., 	"Brown"));
	}


}
