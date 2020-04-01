package de.hsmainz.cs.semgis.arqextension.test.geometry.attribute;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

import de.hsmainz.cs.semgis.arqextension.geometry.attribute.HasRepeatedPoints;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class HasRepeatedPointsTest {

public static final String testGeometry="LINESTRING(0 0, 1 1, 2 4, 1 1,6 8)";

public static final String testGeometry2="LINESTRING(0 0, 1 1, 2 4,6 8)";

	@Test
	public void testHasRepeatedPoints() throws ParseException {
        HasRepeatedPoints instance=new HasRepeatedPoints();
        List<Coordinate> coords=new LinkedList<Coordinate>();
        coords.add(new Coordinate(0.,0.)); 
        WKTReader reader=new WKTReader();
        Geometry geom=reader.read(testGeometry);
        NodeValue input=GeometryWrapperFactory.createLineString(geom.getCoordinates(), WKTDatatype.URI).asNodeValue();
        NodeValue expResult = NodeValue.makeBoolean(true);
        NodeValue result = instance.exec(input);
        assertEquals(expResult, result);
	}

	@Test
	public void testHasRepeatedPoints2() throws ParseException {
        HasRepeatedPoints instance=new HasRepeatedPoints();
        List<Coordinate> coords=new LinkedList<Coordinate>();
        coords.add(new Coordinate(0.,0.)); 
        WKTReader reader=new WKTReader();
        Geometry geom=reader.read(testGeometry2);
        NodeValue input=GeometryWrapperFactory.createLineString(geom.getCoordinates(), WKTDatatype.URI).asNodeValue();
        NodeValue expResult = NodeValue.makeBoolean(false);
        NodeValue result = instance.exec(input);
        assertEquals(expResult, result);
	}
	
}
