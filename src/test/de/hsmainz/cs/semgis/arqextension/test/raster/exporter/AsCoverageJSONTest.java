package de.hsmainz.cs.semgis.arqextension.test.raster.exporter;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.raster.constructor.RastFromCoverageJSON;
import de.hsmainz.cs.semgis.arqextension.raster.exporter.AsCoverageJSON;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CovJSONDatatype;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.HexWKBRastDatatype;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.WKBRastDatatype;

public class AsCoverageJSONTest extends SampleRasters {
	
	@Test
	public void testAsCoverageJSON() {
		NodeValue covLiteral = NodeValue.makeNode(wkbString1,HexWKBRastDatatype.INSTANCE);
        AsCoverageJSON instance=new AsCoverageJSON();
        NodeValue expResult = NodeValue.makeString(covJSONString1);
        NodeValue result = instance.exec(covLiteral);
        assertEquals(expResult, result);
	}

}
