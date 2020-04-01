package de.hsmainz.cs.semgis.arqextension.test.point.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.point.attribute.M;
import de.hsmainz.cs.semgis.arqextension.point.attribute.X;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class XTest {

		public static final String testPoint="POINT(1 2 3 4)";
		
		@Test
		public void testX() {
	        NodeValue geometryLiteral = NodeValue.makeNode(testPoint, WKTDatatype.INSTANCE);
	        X instance=new X();
	        NodeValue expResult = NodeValue.makeDouble(1);
	        NodeValue result = instance.exec(geometryLiteral);
	        assertEquals(expResult, result);
		}
	
}
