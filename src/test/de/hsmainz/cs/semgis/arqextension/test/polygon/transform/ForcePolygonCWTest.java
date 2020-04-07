package de.hsmainz.cs.semgis.arqextension.test.polygon.transform;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.polygon.transform.ForcePolygonCW;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class ForcePolygonCWTest {

	public static final String testPolygon="POLYGON((1 2, 7 8, 5 6, 3 4, 1 2))";
	
	public static final String testPolygon2="POLYGON((1 2, 3 4, 5 6, 1 2))";
	
	@Test
	public void testForcePolygonCW() {
        NodeValue geometryLiteral = NodeValue.makeNode(testPolygon2, WKTDatatype.INSTANCE);
        ForcePolygonCW instance=new ForcePolygonCW();
        NodeValue expResult = NodeValue.makeNode(testPolygon, WKTDatatype.INSTANCE);
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
}
