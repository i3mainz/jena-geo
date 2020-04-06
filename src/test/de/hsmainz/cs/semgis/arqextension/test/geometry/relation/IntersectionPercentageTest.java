package de.hsmainz.cs.semgis.arqextension.test.geometry.relation;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.geometry.relation.IntersectionPercentage;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class IntersectionPercentageTest {

	public static final String testGeom="LINESTRING(77.29 29.07,77.42 29.26,77.27 29.31,77.29 29.07)";
	
	public static final String testGeom2="LINESTRING(77.42 29.26 ,10 10)";
	
	public static final String result="POINT(5 5)";
	
	public static final String isocelesTriangle="POLYGON((1 2, 11 13, 5 6, 1 2))";
	
	public static final String notIsocelesTriangle="POLYGON((1 2, 11 13, 5 5, 1 2))";
	
	@Test
	public void testIntersectionPercentage() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeom, WKTDatatype.INSTANCE);
        NodeValue geometryLiteral2 = NodeValue.makeNode(testGeom2, WKTDatatype.INSTANCE);
        IntersectionPercentage instance=new IntersectionPercentage();
        NodeValue expResult = NodeValue.makeDouble(0.);
        NodeValue result = instance.exec(geometryLiteral,geometryLiteral2);
        assertEquals(expResult, result);
	}
	
	@Test
	public void testIntersectionPercentage2() {
        NodeValue geometryLiteral = NodeValue.makeNode(isocelesTriangle, WKTDatatype.INSTANCE);
        NodeValue geometryLiteral2 = NodeValue.makeNode(notIsocelesTriangle, WKTDatatype.INSTANCE);
        IntersectionPercentage instance=new IntersectionPercentage();
        NodeValue expResult = NodeValue.makeDouble(0.2857142857142857e0);
        NodeValue result = instance.exec(geometryLiteral,geometryLiteral2);
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
}
