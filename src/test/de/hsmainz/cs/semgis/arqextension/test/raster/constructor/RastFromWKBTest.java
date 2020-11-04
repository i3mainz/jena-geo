package de.hsmainz.cs.semgis.arqextension.test.raster.constructor;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.raster.constructor.RastFromWKB;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.WKBRastDatatype;

public class RastFromWKBTest extends SampleRasters {
		
		/*@Test
		public void testRastFromWKB() {
			NodeValue covLiteral = NodeValue.makeString(wkbString1);
	        RastFromWKB instance=new RastFromWKB();
	        NodeValue expResult = NodeValue.makeNode(wkbString1,HexWKBRastDatatype.INSTANCE);
	        NodeValue result = instance.exec(covLiteral);
	        assertEquals(expResult, result);
		}*/

}
