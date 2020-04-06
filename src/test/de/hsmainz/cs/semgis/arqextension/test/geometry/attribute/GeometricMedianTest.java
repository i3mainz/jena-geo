package de.hsmainz.cs.semgis.arqextension.test.geometry.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.io.ParseException;

import de.hsmainz.cs.semgis.arqextension.point.GeometricMedian;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class GeometricMedianTest {

	public static final String testGeometry="MULTIPOINT (( -1 0), (-1 2), (-1 3), (-1 4), (-1 7), (0 1), (0 3), (1 1), (2 0), (6 0), (7 8), (9 8), (10 6))";
	
	@Test
	public void testGeometricMedian() throws ParseException {
        GeometricMedian instance=new GeometricMedian();
        NodeValue input=NodeValue.makeNode(testGeometry, WKTDatatype.INSTANCE);
        NodeValue expResult = NodeValue.makeNode("POINT(0 0)", WKTDatatype.INSTANCE);
        //NodeValue result = instance.exec(input);
        NodeValue result=null;
        assertEquals(expResult, result);
	}
	
}
