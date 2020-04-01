package de.hsmainz.cs.semgis.arqextension.test.geometry.exporter;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.geometry.exporter.AsLatLonText;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class AsLatLonTextTest {

	public static final String testGeometry="POINT(49.9928617 8.2472526)";
	
	@Test
	public void testAsLatLonText() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeometry, WKTDatatype.INSTANCE);
        AsLatLonText instance=new AsLatLonText();
        NodeValue expResult = NodeValue.makeString("49.9928617°59.57170199999993'34.302119999983915\"N 8.2472526°14.83515599999997'50.109359999999015\"E");
        NodeValue result = instance.exec(geometryLiteral,NodeValue.makeString(""));
        assertEquals(expResult, result);
	}

}
