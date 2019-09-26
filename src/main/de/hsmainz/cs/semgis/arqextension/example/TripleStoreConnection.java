package de.hsmainz.cs.semgis.arqextension.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.sparql.expr.NodeValue;
import org.json.JSONArray;
import org.json.JSONObject;

import de.hsmainz.cs.semgis.arqextension.PostGISConfig;
import de.hsmainz.cs.semgis.arqextension.geometry.exporter.AsGeoJSON;
import io.github.galbiston.geosparql_jena.configuration.GeoSPARQLConfig;

public class TripleStoreConnection {

	public static final TripleStoreConnection INSTANCE=new TripleStoreConnection();
	
	public static final String prefixCollection="PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"+System.lineSeparator()+"PREFIX geo: <http://www.opengis.net/ont/geosparql#>";
	
	public final Map<String,OntModel> modelmap;
	
	public static Integer resultSetSize=0;
	
	public TripleStoreConnection() {
		GeoSPARQLConfig.setupMemoryIndex();
		PostGISConfig.setup();
		modelmap=new TreeMap<String,OntModel>();
		modelmap.put("testdata.ttl", ModelFactory.createOntologyModel());
		modelmap.put("linkedgeodata.ttl", ModelFactory.createOntologyModel());
		modelmap.put("rasterexample.ttl", ModelFactory.createOntologyModel());
		//modelmap.put("testdata3.ttl", ModelFactory.createOntologyModel());
		//modelmap.put("testdata4.ttl", ModelFactory.createOntologyModel());
		for(String mod:modelmap.keySet()) {
			try {
				modelmap.get(mod).read(new FileInputStream(mod),null,"TTL");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	
	public static String executeQuery(String query,String model) {
		query=prefixCollection+query;
		System.out.println(query);
		System.out.println(model);
		System.out.println(INSTANCE.modelmap.keySet());
		if(!INSTANCE.modelmap.containsKey(model)) {
			model=INSTANCE.modelmap.keySet().iterator().next();
		}
		try (QueryExecution qe = QueryExecutionFactory.create(query, INSTANCE.modelmap.get(model))) {
		    ResultSet rs = qe.execSelect();
		    List<QuerySolution> test=ResultSetFormatter.toList(rs);
		    JSONArray geojsonresults=new JSONArray();



		    List<JSONArray> allfeatures=new LinkedList<JSONArray>();
		    JSONObject result=new JSONObject();
		    JSONArray obj=new JSONArray();
	    	Boolean first=true;
	    	resultSetSize=test.size();
		    for(QuerySolution solu:test) {
		    	JSONObject jsonobj=new JSONObject();
		    	Iterator<String> varnames = solu.varNames();
		    	JSONObject properties=new JSONObject();
		    	List<JSONObject> geoms=new LinkedList<JSONObject>();
		    	int geomvars=0;
		    	while(varnames.hasNext()) {

		    		String name=varnames.next();
		    		if(name.endsWith("_geom")) {
		    			if(first) {
		    			    JSONObject geojsonresult=new JSONObject();
		    			    geojsonresult.put("type", "FeatureCollection");
		    			    geojsonresult.put("name", name);
		    			    JSONArray features=new JSONArray();
		    			    allfeatures.add(features);

		    			    geojsonresults.put(geojsonresult);
		    			    geojsonresults.getJSONObject(geojsonresults.length()-1).put("features",features);
		    			}
		    			geomvars++;
		    			AsGeoJSON geojson=new AsGeoJSON();
		    			try {
		    			NodeValue val=geojson.exec(NodeValue.makeNode(solu.getLiteral(name).getString(),solu.getLiteral(name).getDatatype()));
		    			JSONObject geomobj=new JSONObject(val.asNode().getLiteralValue().toString());
		    			geoms.add(geomobj);

		    			}catch(Exception e) {
		    				e.printStackTrace();
		    			}
		    		}
		    		jsonobj.put(name, solu.get(name));
		    		properties.put(name, solu.get(name));
		    		obj.put(jsonobj);
		    	}
			    first=false;
		    	int geomcounter=0;
		    	for(JSONObject geom:geoms) {
			    	JSONObject geojsonobj=new JSONObject();
			    	geojsonobj.put("type", "Feature");
			    	geojsonobj.put("properties", properties);
			    	geojsonobj.put("geometry", geom);
			    	allfeatures.get(geomcounter%geomvars).put(geojsonobj);  
			    	geomcounter++;
			    }
		    }	    	
		    result.put("geojson", geojsonresults);
		    result.put("data", obj);
		    result.put("size", test.size());
		    return geojsonresults.toString(2);
		}
	}
	
	public static void main(String[] args) {
		String res=TripleStoreConnection.executeQuery("SELECT ?geom ?wkt WHERE { ?geom geo:asWKT ?wkt . FILTER(!geo:ST_IsCollection(?wkt)) }","testdata2.ttl");
		//System.out.println(res[0]);
		System.out.println(res);
		System.out.println("=====================================================================================================");
		res=TripleStoreConnection.executeQuery("SELECT ?geom ?wkt WHERE { ?geom geo:asWKT ?wkt . FILTER(geo:ST_Area(?wkt)>10) }","testdata2.ttl");
		//System.out.println(res[0]);
		System.out.println(res);
		res=TripleStoreConnection.executeQuery("SELECT ?wkt2 WHERE { ?geom geo:asWKT ?wkt . BIND(geo:ST_YMax(?wkt) AS ?wkt2). FILTER(geo:ST_Area(?wkt)>10) }","testdata2.ttl");
		System.out.println(res);
		System.out.println(res);
	}
	
}
