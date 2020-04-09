package de.hsmainz.cs.semgis.arqextension.test.raster.algebra;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.raster.algebra.SubtractConst;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CovJSONDatatype;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.HexWKBRastDatatype;

public class SubtractConstTest extends SampleRasters {

	public static final String combinedRasterLiteral="";
	
	@Test
	public void testSubtractConst() {
		NodeValue covLiteral = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
        NodeValue covLiteral2 = NodeValue.makeInteger(10);
        NodeValue bandnum = NodeValue.makeInteger(0);
        SubtractConst instance=new SubtractConst();
        NodeValue expResult = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
        NodeValue result = instance.exec(covLiteral,bandnum,covLiteral2);
        assertEquals(expResult, result);
	}

}
