package de.hsmainz.cs.semgis.arqextension.test.raster.relation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.Test;

import de.hsmainz.cs.semgis.arqextension.raster.relation.GreaterIntersects;
import de.hsmainz.cs.semgis.arqextension.raster.relation.MedianIntersects;
import de.hsmainz.cs.semgis.arqextension.raster.relation.ValueIntersects;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.HexWKBRastDatatype;

public class MedianIntersectsTest extends SampleRasters {

	public static final String testPoint="POINT (1 1)";
	
	@Test
	public void testMedianIntersects() {
		NodeValue covLiteral = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
		NodeValue geometryLiteral = NodeValue.makeNode(testPoint, WKTDatatype.INSTANCE);
        MedianIntersects instance=new MedianIntersects();
        NodeValue expResult = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
        System.out.println(SampleRasters.displayRasterSummary(wkbString1));
        NodeValue result = instance.exec(geometryLiteral,covLiteral,NodeValue.makeInteger(0),NodeValue.makeDouble(1));
        assertNotEquals(expResult, result);
	}
	
	@Test
	public void testMedIntersects() {
		NodeValue covLiteral = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
		NodeValue geometryLiteral = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
		MedianIntersects instance=new MedianIntersects();
        NodeValue expResult = NodeValue.TRUE;
        System.out.println(SampleRasters.displayRasterSummary(wkbString1));
        NodeValue result = instance.exec(geometryLiteral,covLiteral,NodeValue.makeInteger(0),NodeValue.makeDouble(1));
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
	@Test
	public void testMedIntersectsTrue() {
		NodeValue covLiteral = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
		NodeValue geometryLiteral = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
		MedianIntersects instance=new MedianIntersects();
        NodeValue expResult = NodeValue.FALSE;
        System.out.println(SampleRasters.displayRasterSummary(wkbString1));
        NodeValue result = instance.exec(geometryLiteral,covLiteral,NodeValue.makeInteger(0),NodeValue.makeDouble(-1));
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
}
