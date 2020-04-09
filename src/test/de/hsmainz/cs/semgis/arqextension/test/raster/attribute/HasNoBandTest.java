package de.hsmainz.cs.semgis.arqextension.test.raster.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.raster.attribute.HasNoBand;
import de.hsmainz.cs.semgis.arqextension.raster.attribute.Height;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CovJSONDatatype;

public class HasNoBandTest extends SampleRasters {
	
	@Test
	public void testRasterHeight() {
        NodeValue covLiteral = NodeValue.makeNode(rasterLiteral1, CovJSONDatatype.INSTANCE);
        NodeValue noband = NodeValue.makeInteger(1);
        HasNoBand instance=new HasNoBand();
        NodeValue expResult = NodeValue.makeDouble(10.);
        NodeValue result = instance.exec(covLiteral,noband);
        assertEquals(expResult, result);
	}
}
