package de.hsmainz.cs.semgis.arqextension.test.raster.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.raster.attribute.BandMetaData;
import de.hsmainz.cs.semgis.arqextension.raster.attribute.MemSize;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.HexWKBRastDatatype;

public class MemSizeTest extends SampleRasters {
	
	@Test
	public void testMemSize() {
		NodeValue covLiteral = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
        MemSize instance=new MemSize();
        NodeValue expResult = NodeValue.makeDouble(10.);
        NodeValue result = instance.exec(covLiteral);
        assertEquals(expResult, result);
	}


}
