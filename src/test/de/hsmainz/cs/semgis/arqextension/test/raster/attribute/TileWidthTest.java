package de.hsmainz.cs.semgis.arqextension.test.raster.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.raster.attribute.TileWidth;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.HexWKBRastDatatype;

public class TileWidthTest extends SampleRasters {
	
	@Test
	public void testTileWidth() {
		NodeValue covLiteral = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
        TileWidth instance=new TileWidth();
        NodeValue expResult = NodeValue.makeInteger(2);
        NodeValue result = instance.exec(covLiteral);
        assertEquals(expResult, result);
	}

}
