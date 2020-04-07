package de.hsmainz.cs.semgis.arqextension.test.polygon.editor;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.polygon.editor.AddRing;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class AddRingTest {

	public static final String duplicateRings="POLYGON((8 2, 11 13, 2 6, 8 2), (8 2, 11 13, 2 6, 8 2))";
	
	public static final String nonduplicateRings="POLYGON ((8 2, 11 13, 2 6, 8 2))";
	
	public static final String ring="LINEARRING (8 2, 11 13, 2 6, 8 2)";
	
	@Test
	public void testAddRing() {
        NodeValue geometryLiteral = NodeValue.makeNode(nonduplicateRings, WKTDatatype.INSTANCE);
        NodeValue add= NodeValue.makeNode(ring, WKTDatatype.INSTANCE);
        AddRing instance=new AddRing();
        NodeValue expResult = NodeValue.makeNode(duplicateRings, WKTDatatype.INSTANCE);
        NodeValue result = instance.exec(geometryLiteral,add);
        assertEquals(expResult, result);
	}
	
}
