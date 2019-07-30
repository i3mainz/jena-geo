package test.de.hsmainz.cs.semgis.arqextension.envelope;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.envelope.BBOXLeftOf;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class BBOXLeftOfTest {

	public static final String testGeom1="LINESTRING (2 3, 5 6)";
	
	public static final String testGeom2="LINESTRING (1 4, 1 7)";
	
	public static final String testGeom3="LINESTRING (6 1, 6 5)";
	
	public static final String testGeom4="LINESTRING (0 0, 4 3)";
	
	
	@Test
	public void testBBOXLeftOf() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeom1, WKTDatatype.INSTANCE);
        NodeValue geometryLiteral1 = NodeValue.makeNode(testGeom2, WKTDatatype.INSTANCE);
        BBOXLeftOf instance=new BBOXLeftOf();
        NodeValue expResult = NodeValue.makeBoolean(false);
        NodeValue result = instance.exec(geometryLiteral,geometryLiteral1);
        assertEquals(expResult, result);
	}

	@Test
	public void testBBOXLeftOf1() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeom2, WKTDatatype.INSTANCE);
        NodeValue geometryLiteral1 = NodeValue.makeNode(testGeom1, WKTDatatype.INSTANCE);
        BBOXLeftOf instance=new BBOXLeftOf();
        NodeValue expResult = NodeValue.makeBoolean(true);
        NodeValue result = instance.exec(geometryLiteral,geometryLiteral1);
        assertEquals(expResult, result);
	}
	
}
