package de.hsmainz.cs.semgis.arqextension.test.polygon.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.polygon.attribute.IsTriangle;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class IsTriangleTest {

	public static final String testPolygon="POLYGON((1 2, 3 4, 5 6, 7 8, 1 2))";
	
	public static final String testPolygon2="POLYGON((1 2, 3 4, 5 6, 1 2))";
	
	@Test
	public void testIsTriangleFalse() {
        NodeValue geometryLiteral = NodeValue.makeNode(testPolygon, WKTDatatype.INSTANCE);
        IsTriangle instance=new IsTriangle();
        NodeValue expResult = NodeValue.FALSE;
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
	@Test
	public void testIsTriangleTrue() {
        NodeValue geometryLiteral = NodeValue.makeNode(testPolygon2, WKTDatatype.INSTANCE);
        IsTriangle instance=new IsTriangle();
        NodeValue expResult = NodeValue.TRUE;
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}

}
