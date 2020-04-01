package de.hsmainz.cs.semgis.arqextension.test.geometry.exporter;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.geometry.exporter.AsOSMLink;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class AsOSMLinkTest {

	public static final String testGeometry="POINT(49.9928617 8.2472526)";
	
	@Test
	public void testAsOSMLink() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeometry, WKTDatatype.INSTANCE);
        AsOSMLink instance=new AsOSMLink();
        NodeValue expResult = NodeValue.makeString("http://www.openstreetmap.org/?minlon=8.2472526&minlat=49.9928617&maxlon=8.2472526&maxlat=49.9928617&mlat=49.9928617&mlon=8.2472526");
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}

}
