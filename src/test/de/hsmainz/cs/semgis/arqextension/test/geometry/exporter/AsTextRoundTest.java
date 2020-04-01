package de.hsmainz.cs.semgis.arqextension.test.geometry.exporter;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.geometry.exporter.AsTextRound;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class AsTextRoundTest {

	public static final String testGeometry="LINESTRING(1.32453 2.65655, 4.96254 5.43341)";
	
	@Test
	public void testAsTextRound() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeometry, WKTDatatype.INSTANCE);
        AsTextRound instance=new AsTextRound();
        NodeValue expResult = NodeValue.makeString("LINESTRING (1.32 2.66, 4.96 5.43)");
        NodeValue round=NodeValue.makeInteger(2);
        NodeValue result = instance.exec(geometryLiteral,round);
        assertEquals(expResult, result);
	}

}
