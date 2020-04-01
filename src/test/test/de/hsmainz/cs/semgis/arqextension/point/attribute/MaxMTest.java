package test.de.hsmainz.cs.semgis.arqextension.point;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.point.attribute.MMax;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class MaxMTest {

		public static final String testPoint="LINESTRING M(1 2 3, 4 5 6,7 8 9)";
		
		@Test
		public void testM() {
	        NodeValue geometryLiteral = NodeValue.makeNode(testPoint, WKTDatatype.INSTANCE);
	        MMax instance=new MMax();
	        NodeValue expResult = NodeValue.makeDouble(9);
	        NodeValue result = instance.exec(geometryLiteral);
	        assertEquals(expResult, result);
		}
	
}
