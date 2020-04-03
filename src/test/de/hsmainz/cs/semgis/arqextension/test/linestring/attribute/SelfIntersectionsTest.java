package de.hsmainz.cs.semgis.arqextension.test.linestring.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.linestring.attribute.SelfIntersections;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class SelfIntersectionsTest {

	public static final String isRing="LINESTRING(0 0, 0 1, 1 1, 1 0, 1 1, 0 0)";
	
	public static final String point="MULTIPOINT((1 1), (0,0))";
	
	@Test
	public void testSelfIntersections() {
        NodeValue geometryLiteral = NodeValue.makeNode(isRing, WKTDatatype.INSTANCE);
        NodeValue geometryLiteral2 = NodeValue.makeNode(point, WKTDatatype.INSTANCE);
        SelfIntersections instance=new SelfIntersections();
        NodeValue expResult = geometryLiteral2;
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
}
