package de.hsmainz.cs.semgis.arqextension.test.raster.relation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.Test;

import de.hsmainz.cs.semgis.arqextension.raster.relation.GreaterIntersects;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.HexWKBRastDatatype;

public class GreaterIntersectsTest extends SampleRasters {

	public static final String testPoint="POINT (1 1)";
	
	@Test
	public void testGreaterIntersects() {
		NodeValue covLiteral = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
		NodeValue geometryLiteral = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
        GreaterIntersects instance=new GreaterIntersects();
        NodeValue expResult = NodeValue.FALSE;
        System.out.println(SampleRasters.displayRasterSummary(wkbString1));
        NodeValue result = instance.exec(geometryLiteral,covLiteral,NodeValue.makeInteger(0),NodeValue.makeDouble(1));
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
	@Test
	public void testGreaterIntersectsTrue() {
		NodeValue covLiteral = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
		NodeValue geometryLiteral = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
        GreaterIntersects instance=new GreaterIntersects();
        NodeValue expResult = NodeValue.FALSE;
        System.out.println(SampleRasters.displayRasterSummary(wkbString1));
        NodeValue result = instance.exec(geometryLiteral,covLiteral,NodeValue.makeInteger(0),NodeValue.makeDouble(-1));
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
}
