package de.hsmainz.cs.semgis.arqextension.test.raster.relation;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.Test;

import de.hsmainz.cs.semgis.arqextension.raster.relation.Equals;
import de.hsmainz.cs.semgis.arqextension.raster.relation.RasterEquals;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.HexWKBRastDatatype;

public class EqualsTest extends SampleRasters {
	
	@Test
	public void testEqualsTrue() {
		NodeValue covLiteral = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
        Equals instance=new Equals();
        NodeValue expResult = NodeValue.TRUE;
        NodeValue result = instance.exec(covLiteral,covLiteral);
        assertEquals(expResult, result);
	}
	
	@Test
	public void testEqualsFalse() {
		NodeValue covLiteral = NodeValue.makeNode(wkbString4, HexWKBRastDatatype.INSTANCE);
		NodeValue covLiteral2 = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
        Equals instance=new Equals();
        NodeValue expResult = NodeValue.FALSE;
        NodeValue result = instance.exec(covLiteral,covLiteral2);
        assertEquals(expResult, result);
	}
	
	@Test
	public void testRasterEqualsTrue() {
		NodeValue covLiteral = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
		NodeValue covLiteral2 = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
        RasterEquals instance=new RasterEquals();
        NodeValue expResult = NodeValue.TRUE;
        NodeValue result = instance.exec(covLiteral,covLiteral2);
        assertEquals(expResult, result);
	}
	
	
	@Test
	public void testRasterEqualsFalse() {
		NodeValue covLiteral = NodeValue.makeNode(wkbString4, HexWKBRastDatatype.INSTANCE);
		NodeValue covLiteral2 = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
        RasterEquals instance=new RasterEquals();
        NodeValue expResult = NodeValue.FALSE;
        NodeValue result = instance.exec(covLiteral,covLiteral2);
        assertEquals(expResult, result);
	}


}
