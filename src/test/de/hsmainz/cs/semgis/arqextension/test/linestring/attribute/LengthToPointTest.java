package de.hsmainz.cs.semgis.arqextension.test.linestring.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.linestring.attribute.LengthToPoint;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class LengthToPointTest {

	public static final String isRing="LINESTRING(0 0, 0 1, 1 1, 1 0, 0 0)";
	
	public static final String point="POINT(1 0)";
	
	@Test
	public void testLengthToPoint() {
        NodeValue geometryLiteral = NodeValue.makeNode(isRing, WKTDatatype.INSTANCE);
        NodeValue geometryLiteral2 = NodeValue.makeNode(point, WKTDatatype.INSTANCE);
        LengthToPoint instance=new LengthToPoint();
        NodeValue expResult = NodeValue.makeDouble(3.0);
        NodeValue result = instance.exec(geometryLiteral,geometryLiteral2);
        assertEquals(expResult, result);
	}

	
}
