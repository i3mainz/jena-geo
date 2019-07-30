package test.de.hsmainz.cs.semgis.arqextension.geometry;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.geometry.attribute.MinimumClearance;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class MinimumClearanceTest {

	public static final String testPolygon="POLYGON ((0 0, 1 0, 1 1, 0.5 3.2e-4, 0 0))";
	
	@Test
	public void testMinimumClearance() {
        NodeValue geometryLiteral = NodeValue.makeNode(testPolygon, WKTDatatype.INSTANCE);
        MinimumClearance instance=new MinimumClearance();
        NodeValue expResult = NodeValue.makeDouble(0.00032);
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
}
