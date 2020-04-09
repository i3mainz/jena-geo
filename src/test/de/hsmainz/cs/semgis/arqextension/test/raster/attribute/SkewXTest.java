package de.hsmainz.cs.semgis.arqextension.test.raster.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.raster.attribute.SkewX;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.HexWKBRastDatatype;

public class SkewXTest extends SampleRasters {
	
	@Test
	public void testSkewX() {
		NodeValue covLiteral = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
        SkewX instance=new SkewX();
        NodeValue expResult = NodeValue.makeDouble(10);
        NodeValue result = instance.exec(covLiteral);
        assertEquals(expResult, result);
	}
}
