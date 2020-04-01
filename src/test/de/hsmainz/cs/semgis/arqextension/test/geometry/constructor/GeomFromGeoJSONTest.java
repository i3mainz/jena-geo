package de.hsmainz.cs.semgis.arqextension.test.geometry.constructor;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.io.ParseException;

import de.hsmainz.cs.semgis.arqextension.geometry.constructor.GeomFromGeoJSON;
import io.github.galbiston.geosparql_jena.implementation.datatype.GeoJSONDatatype;

public class GeomFromGeoJSONTest {

	public static final String geoJsonTestGeom="{\"type\":\"Point\",\"coordinates\":[-48.23456,20.12345]}";
	
	@Test
	public void testGeomFromGeoJSON() throws ParseException {
        GeomFromGeoJSON instance=new GeomFromGeoJSON();
        List<Coordinate> coords=new LinkedList<Coordinate>();
        coords.add(new Coordinate(-48.23456,20.12345)); 
        NodeValue expResult = GeoJSONDatatype.INSTANCE.parse(geoJsonTestGeom).asNodeValue();      
        NodeValue result = instance.exec(NodeValue.makeString(geoJsonTestGeom));
        assertEquals(expResult, result);
	}
	
}

