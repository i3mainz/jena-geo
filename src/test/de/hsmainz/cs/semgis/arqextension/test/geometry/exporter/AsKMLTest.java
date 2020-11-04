package de.hsmainz.cs.semgis.arqextension.test.geometry.exporter;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.geometry.exporter.AsKML;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class AsKMLTest {

	public static final String testGeometry="POLYGON((0 0,0 1,1 1,1 0,0 0))";
	
	/*@Test
	public void testAsKML() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeometry, WKTDatatype.INSTANCE);
        AsKML instance=new AsKML();
        NodeValue expResult = NodeValue.makeString("<Polygon>\n <outerBoundaryIs>\n <LinearRing>\n <coordinates>0.0, 0.0 0.0,1.0 1.0,1.0 1.0,0.0 0.0,0.0</coordinates>\n </LinearRing>\n </outerBoundaryIs>\n </Polygon>\n");
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}*/
	
}
