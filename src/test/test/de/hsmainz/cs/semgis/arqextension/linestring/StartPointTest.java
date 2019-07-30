package test.de.hsmainz.cs.semgis.arqextension.linestring;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import de.hsmainz.cs.semgis.arqextension.linestring.attribute.StartPoint;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class StartPointTest {

	public static final String LineString2D="LINESTRING(0 1, 0 2)";
	
	public static final String point="POINT(0 1)";
	
	public static final String LineString3D="LINESTRING(0 1 1, 0 2 2)";
	
	public static final String CircularString="CIRCULARSTRING(5 2,-3 1.999999, -2 1, -4 2, 5 2)";
	
	@Test
	public void testLineString2D() {
        NodeValue geometryLiteral = NodeValue.makeNode(LineString2D, WKTDatatype.INSTANCE);
        StartPoint instance=new StartPoint();
        NodeValue expResult = GeometryWrapperFactory.createPoint(new Coordinate(0.,1.), WKTDatatype.URI).asNodeValue();
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}

	@Test
	public void testLineString3D() {
        NodeValue geometryLiteral = NodeValue.makeNode(LineString3D, WKTDatatype.INSTANCE);
        StartPoint instance=new StartPoint();
        NodeValue expResult = GeometryWrapperFactory.createPoint(new Coordinate(0.,1.,1.), WKTDatatype.URI).asNodeValue();
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}

	@Test
	public void testCircularString() {
        NodeValue geometryLiteral = NodeValue.makeNode(CircularString, WKTDatatype.INSTANCE);
        StartPoint instance=new StartPoint();
        NodeValue expResult = GeometryWrapperFactory.createPoint(new Coordinate(5.,2.), WKTDatatype.URI).asNodeValue();
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
	@Test
	public void testPoint() {
        NodeValue geometryLiteral = NodeValue.makeNode(point, WKTDatatype.INSTANCE);
        StartPoint instance=new StartPoint();
        NodeValue expResult = GeometryWrapperFactory.createPoint(new Coordinate(0.,1.), WKTDatatype.URI).asNodeValue();
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
}
