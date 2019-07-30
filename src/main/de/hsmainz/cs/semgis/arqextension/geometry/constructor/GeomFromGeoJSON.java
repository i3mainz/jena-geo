package de.hsmainz.cs.semgis.arqextension.geometry.constructor;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Geometry;
import org.wololo.jts2geojson.GeoJSONReader;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; 
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.GeoJSONDatatype;

/**
 * Takes as input a geojson representation of a geometry and outputs a PostGIS geometry object
 *
 */
public class GeomFromGeoJSON extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
		String geojson=v.getString();
		GeoJSONReader reader = new GeoJSONReader();
		Geometry geom = reader.read(geojson);
        GeometryWrapper pointWrapper = GeometryWrapperFactory.createGeometry(geom, "<http://www.opengis.net/def/crs/EPSG/0/"+geom.getSRID()+">", GeoJSONDatatype.URI);	
        return pointWrapper.asNodeValue();  
	}

}
