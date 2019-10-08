package de.hsmainz.cs.semgis.arqextension.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import de.hsmainz.cs.semgis.arqextension.benchmark.BenchmarkExecutor;

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
	@Path("/queryservicegeojson")
    public String queryService(@QueryParam("query") String query,@QueryParam("dataset") String dataset,@QueryParam("geojson") String geojson) { 
		final String dir = System.getProperty("user.dir");
        System.out.println("current dir = " + dir); 
		return TripleStoreConnection.executeQuery(query,dataset,true);
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/hello")
    public String helloWorld() {
		return "HelloWorld";
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/benchmark")
    public String benchmark(@QueryParam("query") String query,@QueryParam("dataset") String dataset) { 
		System.out.println("BENCHMARK QUERY!!!");
		final String dir = System.getProperty("user.dir");
        System.out.println("current dir = " + dir); 
        JSONObject sqlresults=BenchmarkExecutor.benchmark(query,dataset,true);
        JSONObject sparqlresults=BenchmarkExecutor.benchmark(query,dataset,false);
        System.out.println("FINISHED BENCHMARKS!");
		JSONObject result=new JSONObject();
		result.put("sparql",sparqlresults);
		result.put("sql", sqlresults);
		System.out.println("PACKING JSON");
		return result.toString();
	}
	
	public static void main (String[] args) {
		TripleStoreConnection.executeQuery("","testdata2.ttl");
        //JSONObject sparqlresults=BenchmarkExecutor.benchmark("all","all",false);
        //JSONObject sqlresults=BenchmarkExecutor.benchmark("all","all",true);
	}
	
}