package de.hsmainz.cs.semgis.arqextension.test.linestring.transform;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import de.hsmainz.cs.semgis.arqextension.linestring.transform.BezierSmoothing;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class BezierSmoothingTest {

	public static final String testLineString="LINESTRING(1 2,1 10)";
	
	@Test
	public void testBezierSmoothing() {
        NodeValue geometryLiteral = NodeValue.makeNode(testLineString, WKTDatatype.INSTANCE);
        BezierSmoothing instance=new BezierSmoothing();
        List<Coordinate> coords=new LinkedList<Coordinate>();
        coords.add(new Coordinate(1,10));
        coords.add(new Coordinate(1,2));
        NodeValue expResult = GeometryWrapperFactory.createLineString(coords, WKTDatatype.URI).asNodeValue();
        NodeValue result = instance.exec(geometryLiteral,NodeValue.makeDouble(0.5));
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
}
