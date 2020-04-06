package de.hsmainz.cs.semgis.arqextension.test.polygon.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.polygon.attribute.HasDuplicateRings;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class HasDuplicateRingsTest {

public static final String duplicateRings="POLYGON((8 2, 11 13, 2 6, 8 2),(8 2, 11 13, 2 6, 8 2))";
	
	public static final String nonduplicateRings="POLYGON((1 2, 3 4, 5 6, 1 2))";
	
	@Test
	public void testHasDuplicateRingsTrue() {
        NodeValue geometryLiteral = NodeValue.makeNode(duplicateRings, WKTDatatype.INSTANCE);
        HasDuplicateRings instance=new HasDuplicateRings();
        NodeValue expResult = NodeValue.TRUE;
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
	@Test
	public void testHasDuplicateRingsFalse() {
        NodeValue geometryLiteral = NodeValue.makeNode(nonduplicateRings, WKTDatatype.INSTANCE);
        HasDuplicateRings instance=new HasDuplicateRings();
        NodeValue expResult = NodeValue.FALSE;
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
}
