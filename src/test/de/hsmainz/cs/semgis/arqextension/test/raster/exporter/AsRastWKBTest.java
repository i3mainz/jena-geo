package de.hsmainz.cs.semgis.arqextension.test.raster.exporter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.raster.constructor.RastFromCoverageJSON;
import de.hsmainz.cs.semgis.arqextension.raster.constructor.RastFromHexWKB;
import de.hsmainz.cs.semgis.arqextension.raster.exporter.AsHexRastWKB;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.datatype.geometry.HexWKBDatatype;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CovJSONDatatype;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.HexWKBRastDatatype;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.WKBRastDatatype;

public class AsRastWKBTest extends SampleRasters {
	
	@Test
	public void testAsRastWKB() {
		NodeValue covLiteral = NodeValue.makeNode(wkbString1,HexWKBRastDatatype.INSTANCE);
        AsHexRastWKB instance=new AsHexRastWKB();
        NodeValue expResult = NodeValue.makeString(wkbString1);
        NodeValue result = instance.exec(covLiteral);
        assertNotEquals(expResult, result);
	}

}
