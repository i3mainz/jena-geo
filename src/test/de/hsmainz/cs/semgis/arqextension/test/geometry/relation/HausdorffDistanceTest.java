package de.hsmainz.cs.semgis.arqextension.test.geometry.relation;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.geometry.relation.HausdorffDistance;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class HausdorffDistanceTest {

	public static final String testGeom1="LINESTRING (0 0, 2 0)";
	
	public static final String testGeom2="MULTIPOINT (0 1, 1 0, 2 1)";
	
	public static final String testGeom3="LINESTRING (130 0, 0 0, 0 150)";
	
	public static final String testGeom4="LINESTRING (10 10, 10 150, 130 10)";
	
	@Test
	public void testHausdorffDistance() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeom1, WKTDatatype.INSTANCE);
        NodeValue geometryLiteral2 = NodeValue.makeNode(testGeom2, WKTDatatype.INSTANCE);
        HausdorffDistance instance=new HausdorffDistance();
        NodeValue expResult = NodeValue.makeDouble(1.);
        NodeValue result = instance.exec(geometryLiteral,geometryLiteral2);
        assertEquals(expResult, result);
	}
	
}
