package de.hsmainz.cs.semgis.arqextension.test.linestring.attribute;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import de.hsmainz.cs.semgis.arqextension.linestring.attribute.LineSelfIntersectionPoint;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class LineSelfIntersectionPointTest {

	public static final String testGeom="LINESTRING(5 2, 3 8, 6 20, 7 25, 10 10)";
	
	public static final String testGeom2="LINESTRING(5 2, 3 8, 6 20, 7 25, 10 10, 6 20, 7 25)";
	
	public static final String pointempty="POINT EMPTY";
	
	@Test
	public void testLineSelfIntersectionPoint() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeom, WKTDatatype.INSTANCE);
        LineSelfIntersectionPoint instance=new LineSelfIntersectionPoint();
        List<Coordinate> coords=new LinkedList<Coordinate>();
        coords.add(new Coordinate(5.,2.));
        coords.add(new Coordinate(7.,25.));
        coords.add(new Coordinate(10.,10.));
        NodeValue expResult = NodeValue.makeNode(pointempty,WKTDatatype.INSTANCE);
        NodeValue result = instance.exec(geometryLiteral);
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
}
