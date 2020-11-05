package de.hsmainz.cs.semgis.arqextension.test.raster.algebra;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.raster.algebra.Crop;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.HexWKBRastDatatype;

public class CropTest extends SampleRasters {

	public static final String combinedRasterLiteral="";
	
	@Test
	public void testCrop() {
		NodeValue covLiteral = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);       
        Crop instance=new Crop();
        NodeValue expResult = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
        NodeValue result = instance.exec(covLiteral,NodeValue.makeDouble(0),NodeValue.makeDouble(0),NodeValue.makeDouble(2),NodeValue.makeDouble(2));
        System.out.println(displayRasterSummary(wkbString1));
        System.out.println(displayRasterSummary(result));
        assertNotEquals(expResult, result);
	}

}
