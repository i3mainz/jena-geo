package de.hsmainz.cs.semgis.arqextension.test.raster.algebra;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.raster.algebra.Equal;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CovJSONDatatype;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.HexWKBRastDatatype;

public class EqualTest extends SampleRasters {

	public static final String combinedRasterLiteral="";
	
	@Test
	public void testEqual() {
		NodeValue covLiteral = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
		NodeValue covLiteral2 = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
        Equal instance=new Equal();
        NodeValue expResult = NodeValue.makeNode(combinedRasterLiteral, CovJSONDatatype.INSTANCE);
        NodeValue result = instance.exec(covLiteral,covLiteral2);
        assertEquals(expResult, result);
	}

}
