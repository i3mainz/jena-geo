package de.hsmainz.cs.semgis.arqextension.test.geometry.transform;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.geometry.transform.Rotate;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class RotateTest {

public static final String testPolygon="POLYGON((0 0 2,0 5 2,5 0 2,0 0 2))";
	
	public static final String blade="POLYGON((0 0 2,0 5 2,5 0 2,0 0 2))";

	public static final String res="MULTIPOLYGON((0 0 2,0 5 2,5 0 2,0 0 2))";
		
		@Test
		public void testRotate() {
	        NodeValue geometryLiteral = NodeValue.makeNode(testPolygon, WKTDatatype.INSTANCE);
	        NodeValue radians = NodeValue.makeDouble(0.8);
	        Rotate instance=new Rotate();
	        NodeValue expResult = NodeValue.makeNode(res, WKTDatatype.INSTANCE);
	        NodeValue result = instance.exec(geometryLiteral,radians);
	        assertEquals(expResult, result);
		}
	
}
