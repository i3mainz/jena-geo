package de.hsmainz.cs.semgis.arqextension.test.geometry.exporter;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.geometry.exporter.AsHEXEWKB;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class AsHEXEWKBTest {

public static final String testGeometry="POLYGON((0 0,0 1,1 1,1 0,0 0))";
	
	@Test
	public void testAsHexEWKB() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeometry, WKTDatatype.INSTANCE);
        AsHEXEWKB instance=new AsHEXEWKB();
        NodeValue expResult = NodeValue.makeString("000000000300000001000000050000000000000000000000000000000000000000000000003FF00000000000003FF00000000000003FF00000000000003FF0000000000000000000000000000000000000000000000000000000000000");
        NodeValue result = instance.exec(geometryLiteral,NodeValue.makeString("XDR"));
        assertEquals(expResult, result);
	}
	
}
