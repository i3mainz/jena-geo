package de.hsmainz.cs.semgis.arqextension.test.raster.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.raster.attribute.TranslateX;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.HexWKBRastDatatype;

public class TranslateXTest extends SampleRasters {
	
	@Test
	public void testTranslateX() {
		NodeValue covLiteral = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
        TranslateX instance=new TranslateX();
        NodeValue expResult = NodeValue.makeDouble(0);
        NodeValue result = instance.exec(covLiteral);
        assertEquals(expResult, result);
	}

}
