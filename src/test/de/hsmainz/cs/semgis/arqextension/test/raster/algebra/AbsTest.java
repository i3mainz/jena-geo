package de.hsmainz.cs.semgis.arqextension.test.raster.algebra;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.raster.algebra.Abs;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.HexWKBRastDatatype;

public class AbsTest extends SampleRasters {

	@Test
	public void testAbs() {
		NodeValue covLiteral = NodeValue.makeNode(wkbString4, HexWKBRastDatatype.INSTANCE);
		NodeValue covLiteral2 = NodeValue.makeNode(wkbString4, HexWKBRastDatatype.INSTANCE);
        Abs instance=new Abs();
		NodeValue expResult = NodeValue.makeNode(wkbString4, HexWKBRastDatatype.INSTANCE);
        NodeValue result = instance.exec(covLiteral,covLiteral2);
        System.out.println(displayRasterSummary(wkbString4));
        System.out.println(displayRasterSummary(result));
        assertNotEquals(expResult, result);
	}
	
}
