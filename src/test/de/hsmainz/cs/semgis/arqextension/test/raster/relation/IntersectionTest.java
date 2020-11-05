package de.hsmainz.cs.semgis.arqextension.test.raster.relation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.Test;

import de.hsmainz.cs.semgis.arqextension.raster.relation.RasterIntersection;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.HexWKBRastDatatype;

public class IntersectionTest extends SampleRasters {
	
	@Test
	public void testIntersectionEqual() {
		NodeValue covLiteral = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
        RasterIntersection instance=new RasterIntersection();
        NodeValue expResult = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
        NodeValue result = instance.exec(covLiteral,covLiteral,NodeValue.FALSE);
        assertNotEquals(expResult, result);
	}
	
	@Test
	public void testEqualsEmpty() {
		NodeValue covLiteral = NodeValue.makeNode(wkbString4, HexWKBRastDatatype.INSTANCE);
		NodeValue covLiteral2 = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
        RasterIntersection instance=new RasterIntersection();
        NodeValue expResult = NodeValue.makeNode(THE_EMPTY_RASTER, HexWKBRastDatatype.INSTANCE);
        NodeValue result = instance.exec(covLiteral,covLiteral2,NodeValue.FALSE);
        assertEquals(expResult, result);
	}
	
	@Test
	public void testRasterIntersection() {
		NodeValue covLiteral = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
		NodeValue covLiteral2 = NodeValue.makeNode(wkbString3, HexWKBRastDatatype.INSTANCE);
        System.out.println(displayRasterSummary(wkbString1));
        System.out.println(displayRasterSummary(wkbString3));
        RasterIntersection instance=new RasterIntersection();
        NodeValue expResult = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
        NodeValue result = instance.exec(covLiteral,covLiteral2,NodeValue.FALSE);
        System.out.println(displayRasterSummary(result));
        assertNotEquals(expResult, result);
	}

}
