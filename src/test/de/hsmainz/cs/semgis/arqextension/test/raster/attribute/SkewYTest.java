package de.hsmainz.cs.semgis.arqextension.test.raster.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.raster.attribute.SkewY;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CovJSONDatatype;

public class SkewYTest extends SampleRasters {
	
	@Test
	public void testSkewY() {
		NodeValue covLiteral = NodeValue.makeNode(rasterLiteral1, CovJSONDatatype.INSTANCE);
        SkewY instance=new SkewY();
        NodeValue expResult = NodeValue.makeInteger(10);
        NodeValue result = instance.exec(covLiteral);
        assertEquals(expResult, result);
	}

}
