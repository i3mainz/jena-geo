package de.hsmainz.cs.semgis.arqextension.test.geometry.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.geometry.attribute.NumDistinctPoints;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class NumDistinctPointsTest {

	public static final String testGeom="LINESTRING(77.29 29.07,77.42 29.26,77.27 29.31,77.29 29.07)";
	
	@Test
	public void testNumDistinctPoints() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeom, WKTDatatype.INSTANCE);
        NumDistinctPoints instance=new NumDistinctPoints();
        NodeValue expResult = NodeValue.makeInteger(3);
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}

}
