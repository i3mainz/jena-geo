package de.hsmainz.cs.semgis.arqextension.test.raster.algebra;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.raster.algebra.Constant;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.HexWKBRastDatatype;

public class ConstantTest extends SampleRasters {

public static final String combinedRasterLiteral="";
	
	@Test
	public void testConstant() {
        System.out.println(displayRasterSummary(wkbString3));
		NodeValue covLiteral = NodeValue.makeNode(wkbString3, HexWKBRastDatatype.INSTANCE);
        Constant instance=new Constant();
        NodeValue expResult = NodeValue.makeNode(wkbString3, HexWKBRastDatatype.INSTANCE);
        NodeValue result = instance.exec(covLiteral,NodeValue.makeDouble(1));
        System.out.println(displayRasterSummary(result));
        assertNotEquals(expResult, result);
	}
	
}
