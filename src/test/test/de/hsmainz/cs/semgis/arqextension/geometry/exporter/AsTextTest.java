package test.de.hsmainz.cs.semgis.arqextension.geometry.exporter;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.geometry.exporter.AsText;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class AsTextTest {

	public static final String testGeometry="LINESTRING Z(1 2 3, 4 5 6)";
	
	@Test
	public void testAsText() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeometry, WKTDatatype.INSTANCE);
        AsText instance=new AsText();
        NodeValue expResult = NodeValue.makeString("LINESTRING(1 2 3, 4 5 6)");
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
}
