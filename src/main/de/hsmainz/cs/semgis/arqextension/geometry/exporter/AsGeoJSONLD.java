package de.hsmainz.cs.semgis.arqextension.geometry.exporter;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; 
public class AsGeoJSONLD extends FunctionBase1 {

	public static final String geojsonContext="{"+System.lineSeparator()+
			  "\"@context\": {"+System.lineSeparator()+
	    "\"geojson\": \"https://purl.org/geojson/vocab#\","+System.lineSeparator()+
	    "\"Feature\": \"geojson:Feature\","+System.lineSeparator()+
	    "\"FeatureCollection\": \"geojson:FeatureCollection\","+System.lineSeparator()+
	    "\"GeometryCollection\": \"geojson:GeometryCollection\","+System.lineSeparator()+
	    "\"LineString\": \"geojson:LineString\","+System.lineSeparator()+
	    "\"MultiLineString\": \"geojson:MultiLineString\","+System.lineSeparator()+
	    "\"MultiPoint\": \"geojson:MultiPoint\","+System.lineSeparator()+
	    "\"MultiPolygon\": \"geojson:MultiPolygon\","+System.lineSeparator()+
	    "\"Point\": \"geojson:Point\","+System.lineSeparator()+
	    "\"Polygon\": \"geojson:Polygon\","+System.lineSeparator()+
	    "\"bbox\": {"+System.lineSeparator()+
	      "\"@container\": \"@list\","+System.lineSeparator()+
	      "\"@id\": \"geojson:bbox\""+System.lineSeparator()+
	    "},"+System.lineSeparator()+
	    "\"coordinates\": {"+System.lineSeparator()+
	      "\"@container\": \"@list\","+System.lineSeparator()+
	      "\"@id\": \"geojson:coordinates\""+System.lineSeparator()+
	    "},"+System.lineSeparator()+
	    "\"features\": {"+System.lineSeparator()+
	      "\"@container\": \"@set\","+System.lineSeparator()+
	      "\"@id\": \"geojson:features\""+System.lineSeparator()+
	    "},"+System.lineSeparator()+
	    "\"geometry\": \"geojson:geometry\","+System.lineSeparator()+
	    "\"id\": \"@id\","+System.lineSeparator()+
	    "\"properties\": \"geojson:properties\","+System.lineSeparator()+
	    "\"type\": \"@type\","+System.lineSeparator()+
	    "\"description\": \"http://purl.org/dc/terms/description\","+System.lineSeparator()+
	    "\"title\": \"http://purl.org/dc/terms/title\""+System.lineSeparator()+
	  "},"+System.lineSeparator()+
	  "\"type\": \"Feature\","+System.lineSeparator()+
	  "\"id\": \"http://example.com/features/1\","+System.lineSeparator()+
	  "\"geometry\": {\"type\": \"Point\", \"coordinates\": [0.0, 0.0]},"+System.lineSeparator()+
	  "\"properties\": {"+System.lineSeparator()+
	    "\"title\": \"Null Island\","+System.lineSeparator()+
	    "\"description\": \"A fictional island in the Gulf of Guinea\""+System.lineSeparator()+
	  "}"+System.lineSeparator()+
	"}"+System.lineSeparator()+
"}"+System.lineSeparator();
	
	
	@Override
	public NodeValue exec(NodeValue v) {
		try {
            GeometryWrapper geometry = GeometryWrapper.extract(v);
            throw new UnsupportedOperationException("Not supported yet.");
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}


}
