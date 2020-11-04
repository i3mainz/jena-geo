package de.hsmainz.cs.semgis.arqextension.test.geometry.exporter;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.geometry.exporter.AsText;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class AsTextTest {

	public static final String testGeometry="LINESTRING(1 2, 4 5)";
	
	@Test
	public void testAsText() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeometry, WKTDatatype.INSTANCE);
        AsText instance=new AsText();
        NodeValue expResult = NodeValue.makeString("LINESTRING (1 2, 4 5)");
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
}
