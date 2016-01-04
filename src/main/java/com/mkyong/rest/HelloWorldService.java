package com.mkyong.rest;
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.mkyong.service.Main;
 
@Path("/hello")
public class HelloWorldService {
	Main myClass = new Main();
 
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
 
		String output = myClass.getList();
 
		return Response.status(200).entity(output).build();
 
	}
 
}