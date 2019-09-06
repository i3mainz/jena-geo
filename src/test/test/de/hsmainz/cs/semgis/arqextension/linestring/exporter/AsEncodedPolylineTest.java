package test.de.hsmainz.cs.semgis.arqextension.linestring.exporter;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.linestring.exporter.AsEncodedPolyline;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class AsEncodedPolylineTest {

	public static final String testGeometry="LINESTRING(-120.2 38.5,-120.95 40.7,-126.453 43.252)";
	
	@Test
	public void testAsEncodedPolyline() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeometry, WKTDatatype.INSTANCE);
        AsEncodedPolyline instance=new AsEncodedPolyline();
        NodeValue expResult = NodeValue.makeString("|_p~iF~ps|U_ulLnnqC_mqNvxq`@");
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
}
