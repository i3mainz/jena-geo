package de.hsmainz.cs.semgis.arqextension.test.raster.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.raster.attribute.WorldToRasterCoordY;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.HexWKBRastDatatype;

public class WorldToRasterCoordYTest extends SampleRasters {
	
	@Test
	public void testWorldToRasterCoordY() {
		NodeValue covLiteral = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
		NodeValue x = NodeValue.makeInteger(1);
		NodeValue y = NodeValue.makeInteger(1);
		WorldToRasterCoordY instance=new WorldToRasterCoordY();
        NodeValue expResult = NodeValue.makeInteger(10);
        NodeValue result = instance.exec(covLiteral,x,y);
        assertEquals(expResult, result);
	}

}
