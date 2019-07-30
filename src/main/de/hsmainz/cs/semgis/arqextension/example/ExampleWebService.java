package de.hsmainz.cs.semgis.arqextension.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/service")
public class ExampleWebService {

	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/queryservice")
    public String queryService(@QueryParam("query") String query,@QueryParam("dataset") String dataset) { 
		final String dir = System.getProperty("user.dir");
        System.out.println("current dir = " + dir); 
		return TripleStoreConnection.executeQuery(query,dataset);
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/hello")
    public String helloWorld() {
		return "HelloWorld";
	}
	
	
	
	public static void main (String[] args) {
		TripleStoreConnection.executeQuery("","testdata2.ttl");
	}
	
}
