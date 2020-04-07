package de.hsmainz.cs.semgis.arqextension.test.geometry.relation;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.geometry.relation.FrechetDistance;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class FrechetDistanceTest {

	public static final String testGeom1="LINESTRING (0 0, 2 0)";
	
	public static final String testGeom2="MULTIPOINT (0 1, 1 0, 2 1)";
	
	public static final String testGeom3="LINESTRING (130 0, 0 0, 0 150)";
	
	public static final String testGeom4="LINESTRING (10 10, 10 150, 130 10)";
	
	@Test
	public void testFrechetDistance() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeom2, WKTDatatype.INSTANCE);
        NodeValue geometryLiteral2 = NodeValue.makeNode(testGeom2, WKTDatatype.INSTANCE);
        FrechetDistance instance=new FrechetDistance();
        NodeValue expResult = NodeValue.makeDouble(0.);
        NodeValue result = instance.exec(geometryLiteral,geometryLiteral2,NodeValue.makeDouble(0.));
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
	@Test
	public void testFrechetDistance2() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeom3, WKTDatatype.INSTANCE);
        NodeValue geometryLiteral2 = NodeValue.makeNode(testGeom4, WKTDatatype.INSTANCE);
        FrechetDistance instance=new FrechetDistance();
        NodeValue expResult = NodeValue.makeDouble(10.);
        NodeValue result = instance.exec(geometryLiteral,geometryLiteral2,NodeValue.makeDouble(0.));
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
}
