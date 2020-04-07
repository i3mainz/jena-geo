package de.hsmainz.cs.semgis.arqextension.test.polygon.editor;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.polygon.editor.SetRing;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class SetRingTest {

	public static final String duplicateRings="POLYGON((8 2, 11 13, 2 6, 8 2), (8 2, 11 13, 2 6, 8 2))";
	
	public static final String nonduplicateRings="POLYGON((8 2, 11 13, 2 6, 8 2))";
	
	public static final String ring="LINEARRING (8 2, 11 13, 2 7, 8 2)";
	
	@Test
	public void testSetRing() {
        NodeValue geometryLiteral = NodeValue.makeNode(duplicateRings, WKTDatatype.INSTANCE);
        NodeValue geometryLiteral2 = NodeValue.makeNode(ring, WKTDatatype.INSTANCE);
        NodeValue add= NodeValue.makeInteger(0);
        SetRing instance=new SetRing();
        NodeValue expResult = NodeValue.makeNode(nonduplicateRings, WKTDatatype.INSTANCE);
        NodeValue result = instance.exec(geometryLiteral,add,geometryLiteral2);
        assertEquals(expResult, result);
	}
	
}
