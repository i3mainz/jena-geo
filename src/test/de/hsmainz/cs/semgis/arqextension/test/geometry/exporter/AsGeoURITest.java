package de.hsmainz.cs.semgis.arqextension.test.geometry.exporter;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.geometry.exporter.AsGeoURI;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class AsGeoURITest {

	public static final String testGeometry="POINT(49.9928617 8.2472526)";
	
	@Test
	public void testAsGeoURI() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeometry, WKTDatatype.INSTANCE);
        AsGeoURI instance=new AsGeoURI();
        NodeValue expResult = NodeValue.makeString("geo:49.9928617,8.2472526;crs=EPSG:0");
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}

}
