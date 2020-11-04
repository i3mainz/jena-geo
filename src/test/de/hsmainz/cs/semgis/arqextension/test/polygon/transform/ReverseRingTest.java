package de.hsmainz.cs.semgis.arqextension.test.polygon.transform;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.polygon.transform.ReverseRing;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class ReverseRingTest {

	public static final String duplicateRings="POLYGON((8 2, 11 13, 2 6, 8 2),(8 2, 2 6, 11 13, 8 2))";
	
	public static final String nonduplicateRings="POLYGON ((8 2, 11 13, 2 6, 8 2),(8 2, 11 13, 2 6, 8 2))";
	
	/*@Test
	public void testReverseRing() {
        NodeValue geometryLiteral = NodeValue.makeNode(duplicateRings, WKTDatatype.INSTANCE);
        ReverseRing instance=new ReverseRing();
        NodeValue expResult = NodeValue.makeNode(nonduplicateRings, WKTDatatype.INSTANCE);
        NodeValue result = instance.exec(geometryLiteral,NodeValue.makeInteger(0));
        assertEquals(expResult, result);
	}*/
	
}
