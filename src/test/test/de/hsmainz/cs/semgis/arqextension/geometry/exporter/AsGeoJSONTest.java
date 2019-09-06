package test.de.hsmainz.cs.semgis.arqextension.geometry.exporter;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.geometry.exporter.AsGeoJSON;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class AsGeoJSONTest {

	public static final String testGeometry="LINESTRING(1 2 3, 4 5 6)";
	
	@Test
	public void testAsGeoJSON() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeometry, WKTDatatype.INSTANCE);
        AsGeoJSON instance=new AsGeoJSON();
        NodeValue expResult = NodeValue.makeString("{\"type\":\"LineString\",\"coordinates\":[[1,2,3],[4,5,6]]}");
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
}
