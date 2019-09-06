package test.de.hsmainz.cs.semgis.arqextension.point;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.point.attribute.ZMin;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class MinZTest {

		public static final String testPoint="LINESTRING Z(1 2 3, 4 5 6,7 8 9)";
		
		@Test
		public void testX() {
	        NodeValue geometryLiteral = NodeValue.makeNode(testPoint, WKTDatatype.INSTANCE);
	        ZMin instance=new ZMin();
	        NodeValue expResult = NodeValue.makeDouble(3);
	        NodeValue result = instance.exec(geometryLiteral);
	        assertEquals(expResult, result);
		}
	
}
