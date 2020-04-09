package de.hsmainz.cs.semgis.arqextension.test.raster.algebra;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.raster.algebra.Or;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CovJSONDatatype;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.HexWKBRastDatatype;

public class OrTest extends SampleRasters {

	public static final String combinedRasterLiteral="";
	
	@Test
	public void testOr() {
		NodeValue covLiteral = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
		NodeValue covLiteral2 = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
        Or instance=new Or();
        NodeValue expResult = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
        NodeValue result = instance.exec(covLiteral,covLiteral2);
        assertEquals(expResult, result);
	}

}
