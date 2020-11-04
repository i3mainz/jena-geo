package de.hsmainz.cs.semgis.arqextension.test.raster.exporter;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.raster.exporter.AsHexRastWKB;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.HexWKBRastDatatype;


public class AsHexRastWKBTest extends SampleRasters {
	
	public static final String result="0100000100000000000000F03F000000000000F03F0000000000000000000000000000000000000000000000000000000000000000E610000002000200040000010100";
	
	@Test
	public void testAsHexRastWKB() {
		NodeValue covLiteral = NodeValue.makeNode(wkbString1,HexWKBRastDatatype.INSTANCE);
        AsHexRastWKB instance=new AsHexRastWKB();
        NodeValue expResult = NodeValue.makeString(result);
        NodeValue result = instance.exec(covLiteral);
        System.out.println(wkbString1);
        System.out.println(result);
        assertEquals(expResult, result);
	}

}
