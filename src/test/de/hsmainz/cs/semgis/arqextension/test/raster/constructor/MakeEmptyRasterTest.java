package de.hsmainz.cs.semgis.arqextension.test.raster.constructor;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.raster.constructor.MakeEmptyRaster;
import de.hsmainz.cs.semgis.arqextension.raster.constructor.RastFromCoverageJSON;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CovJSONDatatype;

public class MakeEmptyRasterTest extends SampleRasters {
	
	@Test
	public void testMakeEmptyRaster() {
		NodeValue covLiteral = NodeValue.makeString(covJSONString1);
        MakeEmptyRaster instance=new MakeEmptyRaster();
        //NodeValue expResult = NodeValue.makeNode(rasterLiteral1,CovJSONDatatype.INSTANCE);
        NodeValue result = instance.exec();
        assertEquals("", result);
	}

}
