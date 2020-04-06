package de.hsmainz.cs.semgis.arqextension.test.geometry.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.geometry.attribute.MinimumBoundingCircleCenter;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class MinimumBoundingCircleCenterTest {

	public static final String testPolygon="POLYGON((26426 65078,26531 65242,26075 65136,26096 65427,26426 65078))";
	
	public static final String result="POINT(26284.841802713276 65267.11450908256)";
	
	@Test
	public void testMinimumBoundingCircleCenter() {
        NodeValue geometryLiteral = NodeValue.makeNode(testPolygon, WKTDatatype.INSTANCE);
        MinimumBoundingCircleCenter instance=new MinimumBoundingCircleCenter();
        NodeValue expResult = NodeValue.makeNode(result,WKTDatatype.INSTANCE);
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
}
