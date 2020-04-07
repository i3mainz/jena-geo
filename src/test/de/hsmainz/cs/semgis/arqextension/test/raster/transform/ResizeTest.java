package de.hsmainz.cs.semgis.arqextension.test.raster.transform;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.raster.transform.Resize;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CovJSONDatatype;

public class ResizeTest extends SampleRasters {
	
	@Test
	public void testRasterValue() {
		NodeValue covLiteral = NodeValue.makeNode(rasterLiteral1, CovJSONDatatype.INSTANCE);
        Resize instance=new Resize();
        NodeValue width = NodeValue.makeDouble(10);
        NodeValue height = NodeValue.makeDouble(10);
        NodeValue expResult = NodeValue.makeDouble(10.);
        NodeValue result = instance.exec(covLiteral,width,height);
        assertEquals(expResult, result);
	}

}
