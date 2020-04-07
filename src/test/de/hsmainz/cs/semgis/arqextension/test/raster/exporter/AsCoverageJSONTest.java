package de.hsmainz.cs.semgis.arqextension.test.raster.exporter;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.raster.constructor.RastFromCoverageJSON;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CovJSONDatatype;

public class AsCoverageJSONTest extends SampleRasters {
	
	@Test
	public void testAsCoverageJSON() {
		NodeValue covLiteral = NodeValue.makeNode(rasterLiteral1,CovJSONDatatype.INSTANCE);
        RastFromCoverageJSON instance=new RastFromCoverageJSON();
        NodeValue expResult = NodeValue.makeString(covJSONString1);
        NodeValue result = instance.exec(covLiteral);
        assertEquals(expResult, result);
	}

}
