package test.de.hsmainz.cs.semgis.arqextension.geometry;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.geometry.ShortestLine;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class ShortestLineTest {

	public static final String testPoint="POINT(100 100)";
	
	public static final String testLineString="LINESTRING (20 80, 98 190, 110 180, 50 75 )";
	
	@Test
	public void testOrderingEquals1() {
        NodeValue geometryLiteral = NodeValue.makeNode(testPoint, WKTDatatype.INSTANCE);
        NodeValue geometryLiteral2 = NodeValue.makeNode(testLineString, WKTDatatype.INSTANCE);
        ShortestLine instance=new ShortestLine();
        NodeValue expResult = NodeValue.makeBoolean(false);
        NodeValue result = instance.exec(geometryLiteral,geometryLiteral2);
        assertEquals(expResult, result);
	}
	
}
