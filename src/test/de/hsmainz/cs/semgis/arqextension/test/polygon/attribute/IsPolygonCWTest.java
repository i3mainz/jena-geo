package de.hsmainz.cs.semgis.arqextension.test.polygon.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.polygon.attribute.IsPolygonCW;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class IsPolygonCWTest {

	public static final String testPolygon="POLYGON((1 2, 7 8, 5 6, 3 4, 1 2))";
	
	public static final String testPolygon2="POLYGON((1 2, 3 4, 5 6, 1 2))";
	
	@Test
	public void testIsPolygonCWTrue() {
        NodeValue geometryLiteral = NodeValue.makeNode(testPolygon, WKTDatatype.INSTANCE);
        IsPolygonCW instance=new IsPolygonCW();
        NodeValue expResult = NodeValue.TRUE;
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
	@Test
	public void testIsPolygonCWFalse() {
        NodeValue geometryLiteral = NodeValue.makeNode(testPolygon2, WKTDatatype.INSTANCE);
        IsPolygonCW instance=new IsPolygonCW();
        NodeValue expResult = NodeValue.FALSE;
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
}
