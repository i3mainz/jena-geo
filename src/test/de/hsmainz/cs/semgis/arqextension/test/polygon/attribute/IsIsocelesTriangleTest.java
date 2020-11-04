package de.hsmainz.cs.semgis.arqextension.test.polygon.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.polygon.attribute.IsIsocelesTriangle;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class IsIsocelesTriangleTest {

	public static final String isocelesTriangle="POLYGON((8 2, 11 56, 2 6, 8 2))";
	
	public static final String notIsocelesTriangle="POLYGON((1 2, 3 4, 5 6, 1 2))";
	
	@Test
	public void testIsIsocelesTriangleFalse() {
        NodeValue geometryLiteral = NodeValue.makeNode(isocelesTriangle, WKTDatatype.INSTANCE);
        IsIsocelesTriangle instance=new IsIsocelesTriangle();
        NodeValue expResult = NodeValue.FALSE;
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
	@Test
	public void testIsIsocelesTriangleTrue() {
        NodeValue geometryLiteral = NodeValue.makeNode(notIsocelesTriangle, WKTDatatype.INSTANCE);
        IsIsocelesTriangle instance=new IsIsocelesTriangle();
        NodeValue expResult = NodeValue.TRUE;
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}

}
