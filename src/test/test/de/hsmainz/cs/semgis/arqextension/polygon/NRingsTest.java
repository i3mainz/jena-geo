package test.de.hsmainz.cs.semgis.arqextension.polygon;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.polygon.NRings;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class NRingsTest {

	public static final String testPolygon="POLYGON((1 2, 3 4, 5 6, 1 2))";
	
	@Test
	public void testPolygon() {
        NodeValue geometryLiteral = NodeValue.makeNode(testPolygon, WKTDatatype.INSTANCE);
        NRings instance=new NRings();
        NodeValue expResult = NodeValue.makeInteger(1);
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
}
