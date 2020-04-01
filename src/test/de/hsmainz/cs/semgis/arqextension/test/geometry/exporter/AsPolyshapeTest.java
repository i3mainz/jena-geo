package de.hsmainz.cs.semgis.arqextension.test.geometry.exporter;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.geometry.exporter.AsPolyshape;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class AsPolyshapeTest {

	public static final String testGeometry="POINT(49.9928617 8.2472526)";
	
	@Test
	public void testAsPolyShape() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeometry, WKTDatatype.INSTANCE);
        AsPolyshape instance=new AsPolyshape();
        NodeValue expResult = NodeValue.makeString("0kfcpHixiq@");
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}

}
