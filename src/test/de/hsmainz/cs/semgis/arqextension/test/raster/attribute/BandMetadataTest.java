package de.hsmainz.cs.semgis.arqextension.test.raster.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.raster.attribute.BandMetaData;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.HexWKBRastDatatype;

public class BandMetadataTest extends SampleRasters {
	
	@Test
	public void testRasterHeight() {
		NodeValue covLiteral = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
        NodeValue noband = NodeValue.makeInteger(10);
        BandMetaData instance=new BandMetaData();
        NodeValue expResult = NodeValue.makeString("");
        NodeValue result = instance.exec(covLiteral,noband);
        assertEquals(expResult, result);
	}

}
