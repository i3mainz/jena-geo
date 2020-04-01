package de.hsmainz.cs.semgis.arqextension.test.geometry.exporter;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.geometry.exporter.AsTextRaw;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class AsTextRawTest {

	public static final String testGeometry="LINESTRING(1.32453 2.65655, 4.96254 5.43341)";
	
	@Test
	public void testAsTextRaw() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeometry, WKTDatatype.INSTANCE);
        AsTextRaw instance=new AsTextRaw();
        NodeValue expResult = NodeValue.makeString("LINESTRING (1.32453 2.65655, 4.96254 5.43341)");
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}

}
