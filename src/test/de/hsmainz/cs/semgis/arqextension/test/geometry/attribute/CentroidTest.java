package de.hsmainz.cs.semgis.arqextension.test.geometry.attribute;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import de.hsmainz.cs.semgis.arqextension.geometry.attribute.Centroid;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class CentroidTest {

	public static final String testGeometry="MULTIPOINT (( -1 0), (-1 2), (-1 3), (-1 4), (-1 7), (0 1), (0 3), (1 1), (2 0), (6 0), (7 8), (9 8), (10 6))";

	@Test
	public void testCentroid() {
        Centroid instance=new Centroid();
        List<Coordinate> coords=new LinkedList<Coordinate>();
        coords.add(new Coordinate(-1., 0.));
        coords.add(new Coordinate(-1., 2.));
        coords.add(new Coordinate(-1., 3.));
        coords.add(new Coordinate(-1., 4.));
        coords.add(new Coordinate(-1., 7.));
        coords.add(new Coordinate(0., 1.));
        coords.add(new Coordinate(0., 3.));
        coords.add(new Coordinate(1., 1.));
        coords.add(new Coordinate(2., 0.));
        coords.add(new Coordinate(6., 0.));
        coords.add(new Coordinate(7., 8.));
        coords.add(new Coordinate(9., 8.));
        coords.add(new Coordinate(10., 6.));
        NodeValue geom2 = GeometryWrapperFactory.createMultiPoint(coords, WKTDatatype.URI).asNodeValue();
        NodeValue expResult = GeometryWrapperFactory.createPoint(new Coordinate(2.30769230769231,3.30769230769231), WKTDatatype.URI).asNodeValue();
        NodeValue result = instance.exec(geom2);
        assertEquals(expResult, result);
	}
	
}
