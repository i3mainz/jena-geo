package de.hsmainz.cs.semgis.arqextension.test.raster.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.raster.attribute.IsEmpty;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.HexWKBRastDatatype;

public class IsEmptyTest extends SampleRasters {
	
	@Test
	public void testRasterIsEmptyFalse() {
		NodeValue covLiteral = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
        IsEmpty instance=new IsEmpty();
        NodeValue expResult = NodeValue.FALSE;
        NodeValue result = instance.exec(covLiteral);
        assertEquals(expResult, result);
	}
	
}
