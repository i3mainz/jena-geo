package de.hsmainz.cs.semgis.arqextension.test.raster.constructor;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.raster.constructor.RastFromHexWKB;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CovJSONDatatype;

public class RastFromHexWKBTest extends SampleRasters {
	
	@Test
	public void testTileHeight() {
		NodeValue covLiteral = NodeValue.makeString(wkbString1);
        RastFromHexWKB instance=new RastFromHexWKB();
        NodeValue expResult = NodeValue.makeNode(rasterLiteral1,CovJSONDatatype.INSTANCE);
        NodeValue result = instance.exec(covLiteral);
        assertEquals(expResult, result);
	}

}
