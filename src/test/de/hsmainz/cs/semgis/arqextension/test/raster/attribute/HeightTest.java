package de.hsmainz.cs.semgis.arqextension.test.raster.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.raster.attribute.Height;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.HexWKBRastDatatype;

public class HeightTest extends SampleRasters {
	
	@Test
	public void testRasterHeight() {
		NodeValue covLiteral = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
        Height instance=new Height();
        NodeValue expResult = NodeValue.makeDouble(10.);
        NodeValue result = instance.exec(covLiteral);
        assertEquals(expResult, result);
	}
	
}
