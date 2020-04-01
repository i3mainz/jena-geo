package de.hsmainz.cs.semgis.arqextension.test.geometry.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.geometry.attribute.MinimumBoundingRadius;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class MinimumBoundingRadiusTest {


	public static final String testPolygon="POLYGON((26426 65078,26531 65242,26075 65136,26096 65427,26426 65078))";
	
	@Test
	public void testMinimumBoundingRadius() {
        NodeValue geometryLiteral = NodeValue.makeNode(testPolygon, WKTDatatype.INSTANCE);
        MinimumBoundingRadius instance=new MinimumBoundingRadius();
        NodeValue expResult = NodeValue.makeDouble(247.4360455914027);
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
}
