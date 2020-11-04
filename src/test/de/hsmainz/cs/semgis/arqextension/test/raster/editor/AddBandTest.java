package de.hsmainz.cs.semgis.arqextension.test.raster.editor;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.raster.editor.AddBand;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CovJSONDatatype;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.HexWKBRastDatatype;

public class AddBandTest extends SampleRasters {
	
	/*@Test
	public void testAddBand() {
		NodeValue covLiteral = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
		NodeValue rasterBandd = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
        AddBand instance=new AddBand();
        NodeValue expResult = NodeValue.makeInteger(10);
        NodeValue result = instance.exec(covLiteral,rasterBandd);
        assertEquals(expResult, result);
	}*/
}
