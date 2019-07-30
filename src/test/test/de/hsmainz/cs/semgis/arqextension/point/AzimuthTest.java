package test.de.hsmainz.cs.semgis.arqextension.point;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import de.hsmainz.cs.semgis.arqextension.point.Azimuth;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class AzimuthTest {

	public static final String testPoint1="POINT(25, 45)";
	
	public static final String testPoint2="POINT(75, 100)";

	@Test
	public void testAzimuth1() {
        Azimuth instance=new Azimuth();
        NodeValue geom1 = GeometryWrapperFactory.createPoint(new Coordinate(25.,45.), WKTDatatype.URI).asNodeValue();
        NodeValue geom2 = GeometryWrapperFactory.createPoint(new Coordinate(75.,100.), WKTDatatype.URI).asNodeValue();
        NodeValue expResult = NodeValue.makeDouble(42.2736890060937);
        NodeValue result = instance.exec(geom1,geom2);
        assertEquals(expResult, result);
	}

	@Test
	public void testAzimuth2() {
        Azimuth instance=new Azimuth();
        NodeValue geom1 = GeometryWrapperFactory.createPoint(new Coordinate(25.,45.), WKTDatatype.URI).asNodeValue();
        NodeValue geom2 = GeometryWrapperFactory.createPoint(new Coordinate(75.,100.), WKTDatatype.URI).asNodeValue();
        NodeValue expResult = NodeValue.makeDouble(222.273689006094);
        NodeValue result = instance.exec(geom2,geom1);
        assertEquals(expResult, result);
	}
	
}
