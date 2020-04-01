package de.hsmainz.cs.semgis.arqextension.test.polygon.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.polygon.attribute.CircularityIndex;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class CircularityIndexTest {

	public static final String isocelesTriangle="POLYGON((8 2, 11 13, 2 6, 8 2))";
	
	public static final String notIsocelesTriangle="POLYGON((1 2, 3 4, 5 6, 1 2))";
	
	@Test
	public void testIsScaleneTriangleTrue() {
        NodeValue geometryLiteral = NodeValue.makeNode(isocelesTriangle, WKTDatatype.INSTANCE);
        CircularityIndex instance=new CircularityIndex();
        NodeValue expResult = NodeValue.makeDouble(0.5440126910331675e0);
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
	@Test
	public void testIsScaleneTriangleFalse() {
        NodeValue geometryLiteral = NodeValue.makeNode(notIsocelesTriangle, WKTDatatype.INSTANCE);
        CircularityIndex instance=new CircularityIndex();
        NodeValue expResult = NodeValue.makeDouble(0.0);
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}


}
