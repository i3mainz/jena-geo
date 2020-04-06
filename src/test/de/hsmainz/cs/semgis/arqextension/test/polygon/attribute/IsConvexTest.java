package de.hsmainz.cs.semgis.arqextension.test.polygon.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.polygon.attribute.IsConvex;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class IsConvexTest {

	public static final String isocelesTriangle="POLYGON((8 2, 11 13, 2 6, 8 2))";
	
	public static final String notIsocelesTriangle="POLYGON((1 2, 3 4, 5 6, 1 2))";
	
	@Test
	public void testIsConvexTrue() {
        NodeValue geometryLiteral = NodeValue.makeNode(isocelesTriangle, WKTDatatype.INSTANCE);
        IsConvex instance=new IsConvex();
        NodeValue expResult = NodeValue.TRUE;
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
	@Test
	public void testIsConvexFalse() {
        NodeValue geometryLiteral = NodeValue.makeNode(notIsocelesTriangle, WKTDatatype.INSTANCE);
        IsConvex instance=new IsConvex();
        NodeValue expResult = NodeValue.FALSE;
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
}
