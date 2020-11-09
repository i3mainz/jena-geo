package de.hsmainz.cs.semgis.arqextension.test.raster.algebra;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.raster.algebra.Clamp;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.HexWKBRastDatatype;

public class ClampTest extends SampleRasters {

	public static final String combinedRasterLiteral="";
	
	@Test
	public void testClamp() {
        System.out.println(displayRasterSummary(wkbString4));
		NodeValue covLiteral = NodeValue.makeNode(wkbString4, HexWKBRastDatatype.INSTANCE);
		System.out.println(covLiteral);
        Clamp instance=new Clamp();
        NodeValue expResult = NodeValue.makeNode(wkbString4, HexWKBRastDatatype.INSTANCE);
        NodeValue result = instance.exec(covLiteral,NodeValue.makeInteger(1),NodeValue.makeDouble(253),NodeValue.makeDouble(254));
        System.out.println(displayRasterSummary(result));
        assertNotEquals(expResult, result);
	}

}
