package de.hsmainz.cs.semgis.arqextension.test.geometry.relation;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.geometry.relation.IsMorePrecise;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class IsMorePreciseTest {

	public static final String testGeom="LINESTRING(77.29 29.07,77.42 29.26,77.27 29.31,77.29 29.07)";
	
	public static final String testGeom2="LINESTRING(5 5 ,10 10)";
	
	/*@Test
	public void testIsMorePreciseFirst() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeom, WKTDatatype.INSTANCE);
        NodeValue geometryLiteral2 = NodeValue.makeNode(testGeom2, WKTDatatype.INSTANCE);
        IsMorePrecise instance=new IsMorePrecise();
        NodeValue expResult = NodeValue.makeInteger(-1);
        NodeValue result = instance.exec(geometryLiteral2,geometryLiteral);
        assertEquals(expResult, result);
	}

	@Test
	public void testIsMorePreciseSecond() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeom, WKTDatatype.INSTANCE);
        NodeValue geometryLiteral2 = NodeValue.makeNode(testGeom2, WKTDatatype.INSTANCE);
        IsMorePrecise instance=new IsMorePrecise();
        NodeValue expResult = NodeValue.makeInteger(1);
        NodeValue result = instance.exec(geometryLiteral,geometryLiteral2);
        assertEquals(expResult, result);
	}*/
	
	@Test
	public void testIsMorePreciseEqual() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeom, WKTDatatype.INSTANCE);
        IsMorePrecise instance=new IsMorePrecise();
        NodeValue expResult = NodeValue.makeInteger(0);
        NodeValue result = instance.exec(geometryLiteral,geometryLiteral);
        assertEquals(expResult, result);
	}

}
