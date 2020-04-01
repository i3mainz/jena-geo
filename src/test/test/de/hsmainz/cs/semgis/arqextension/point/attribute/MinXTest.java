package test.de.hsmainz.cs.semgis.arqextension.point;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.point.attribute.XMin;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class MinXTest {

		public static final String testPoint="LINESTRING(1 2,3 4,5 6,7 8)";
		
		@Test
		public void testX() {
	        NodeValue geometryLiteral = NodeValue.makeNode(testPoint, WKTDatatype.INSTANCE);
	        XMin instance=new XMin();
	        NodeValue expResult = NodeValue.makeDouble(1);
	        NodeValue result = instance.exec(geometryLiteral);
	        assertEquals(expResult, result);
		}
	
}
