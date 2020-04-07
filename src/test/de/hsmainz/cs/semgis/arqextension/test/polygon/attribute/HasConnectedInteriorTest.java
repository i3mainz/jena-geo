package de.hsmainz.cs.semgis.arqextension.test.polygon.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.polygon.attribute.HasConnectedInterior;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class HasConnectedInteriorTest {

	public static final String duplicateRings="POLYGON((8 2, 11 13, 2 6, 8 2),(8 2, 11 13, 2 6, 8 2))";
	
	public static final String nonduplicateRings="POLYGON((1 2, 3 4, 5 6, 1 2))";
	
	@Test
	public void testHasConnectedInteriorTrue() {
        NodeValue geometryLiteral = NodeValue.makeNode(duplicateRings, WKTDatatype.INSTANCE);
        HasConnectedInterior instance=new HasConnectedInterior();
        NodeValue expResult = NodeValue.TRUE;
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
	@Test
	public void testHasConnectedInteriorFalse() {
        NodeValue geometryLiteral = NodeValue.makeNode(nonduplicateRings, WKTDatatype.INSTANCE);
        HasConnectedInterior instance=new HasConnectedInterior();
        NodeValue expResult = NodeValue.FALSE;
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
}
