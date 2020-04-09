package de.hsmainz.cs.semgis.arqextension.test.raster.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.raster.attribute.TileGridXOffset;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.HexWKBRastDatatype;

public class TileGridXOffsetTest extends SampleRasters {
	
	@Test
	public void testTileGridXOffset() {
		NodeValue covLiteral = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
        TileGridXOffset instance=new TileGridXOffset();
        NodeValue expResult = NodeValue.makeInteger(0);
        NodeValue result = instance.exec(covLiteral);
        assertEquals(expResult, result);
	}

}
