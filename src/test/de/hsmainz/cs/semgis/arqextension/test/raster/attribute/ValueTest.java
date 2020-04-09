package de.hsmainz.cs.semgis.arqextension.test.raster.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.raster.Value;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.HexWKBRastDatatype;

public class ValueTest extends SampleRasters {
	
	@Test
	public void testRasterValue() {
		NodeValue covLiteral = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
        Value instance=new Value();
        NodeValue cellx = NodeValue.makeDouble(10);
        NodeValue celly = NodeValue.makeDouble(10);
        NodeValue band = NodeValue.makeDouble(0);
        NodeValue expResult = NodeValue.makeDouble(10.);
        NodeValue result = instance.exec(covLiteral,band,cellx,celly);
        assertEquals(expResult, result);
	}

}
