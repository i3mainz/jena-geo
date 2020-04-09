package de.hsmainz.cs.semgis.arqextension.test.raster.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.raster.attribute.HasNoBand;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.HexWKBRastDatatype;

public class HasNoBandTest extends SampleRasters {
	
	@Test
	public void testRasterHeight() {
		NodeValue covLiteral = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
        NodeValue noband = NodeValue.makeInteger(10);
        HasNoBand instance=new HasNoBand();
        NodeValue expResult = NodeValue.FALSE;
        NodeValue result = instance.exec(covLiteral,noband);
        assertEquals(expResult, result);
	}
}
