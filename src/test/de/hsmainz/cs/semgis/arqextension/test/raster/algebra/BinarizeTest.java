package de.hsmainz.cs.semgis.arqextension.test.raster.algebra;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;


import de.hsmainz.cs.semgis.arqextension.raster.algebra.Binarize;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.HexWKBRastDatatype;

public class BinarizeTest extends SampleRasters {

	public static final String combinedRasterLiteral="";
	
	@Test
	public void testBinarize() {
        System.out.println(displayRasterSummary(wkbString3));
		NodeValue covLiteral = NodeValue.makeNode(wkbString3, HexWKBRastDatatype.INSTANCE);
		NodeValue covLiteral2 = NodeValue.makeInteger(31);
        Binarize instance=new Binarize();
        NodeValue expResult = NodeValue.makeNode(wkbString3, HexWKBRastDatatype.INSTANCE);
        NodeValue result = instance.exec(covLiteral,NodeValue.makeInteger(1),covLiteral2);
        System.out.println(displayRasterSummary(result));
        assertNotEquals(expResult, result);
	}

}
