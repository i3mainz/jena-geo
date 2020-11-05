package de.hsmainz.cs.semgis.arqextension.test.raster.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.raster.attribute.MinValue;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.HexWKBRastDatatype;

public class MinValueTest extends SampleRasters {
	
	@Test
	public void testMinValue() {
		NodeValue covLiteral = NodeValue.makeNode(wkbString4, HexWKBRastDatatype.INSTANCE);
        MinValue instance=new MinValue();
        NodeValue expResult = NodeValue.makeDouble(249);
        NodeValue result = instance.exec(covLiteral,NodeValue.makeInteger(0));
        assertEquals(expResult, result);
	}

}
