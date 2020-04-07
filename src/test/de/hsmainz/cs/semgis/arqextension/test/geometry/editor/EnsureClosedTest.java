package de.hsmainz.cs.semgis.arqextension.test.geometry.editor;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.geometry.editor.EnsureClosed;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class EnsureClosedTest {

	public static final String duplicateRings="POLYGON((8 2, 11 13, 2 6, 8 2), (8 2, 11 13, 2 6, 8 2))";
	
	public static final String nonClosed="LINESTRING(8 2, 11 13, 2 6)";
	
	public static final String closed="LINESTRING(8 2, 11 13, 2 6, 8 2)";
	
	public static final String ring="LINEARRING (8 2, 11 13, 2 6, 8 2)";
	
	@Test
	public void testEnsureClosed() {
        NodeValue geometryLiteral = NodeValue.makeNode(nonClosed, WKTDatatype.INSTANCE);
        EnsureClosed instance=new EnsureClosed();
        NodeValue expResult = NodeValue.makeNode(closed, WKTDatatype.INSTANCE);
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
	@Test
	public void testEnsureClosed2() {
        NodeValue geometryLiteral = NodeValue.makeNode(closed, WKTDatatype.INSTANCE);
        EnsureClosed instance=new EnsureClosed();
        NodeValue expResult = NodeValue.makeNode(closed, WKTDatatype.INSTANCE);
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
}
