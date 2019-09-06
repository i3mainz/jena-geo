package test.de.hsmainz.cs.semgis.arqextension.linestring.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import de.hsmainz.cs.semgis.arqextension.linestring.attribute.EndPoint;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class EndPointTest {

	public static final String LineString2D="LINESTRING(1 1, 2 2, 3 3)";
	
	public static final String point="POINT(1 1)";
	
	public static final String LineString3D="LINESTRING(1 1 2, 1 2 3, 0 0 5)";
	
	@Test
	public void testLineString2D() {
        NodeValue geometryLiteral = NodeValue.makeNode(LineString2D, WKTDatatype.INSTANCE);
        EndPoint instance=new EndPoint();
        NodeValue expResult = GeometryWrapperFactory.createPoint(new Coordinate(3.,3.), WKTDatatype.URI).asNodeValue();
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}

	@Test
	public void testLineString3D() {
        NodeValue geometryLiteral = NodeValue.makeNode(LineString3D, WKTDatatype.INSTANCE);
        EndPoint instance=new EndPoint();
        NodeValue expResult = GeometryWrapperFactory.createPoint(new Coordinate(0.,0.,5.), WKTDatatype.URI).asNodeValue();
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}

	@Test
	public void testPoint() {
        NodeValue geometryLiteral = NodeValue.makeNode(point, WKTDatatype.INSTANCE);
        EndPoint instance=new EndPoint();
        NodeValue expResult = GeometryWrapperFactory.createPoint(new Coordinate(1.,1.), WKTDatatype.URI).asNodeValue();
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
}
