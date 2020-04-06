package de.hsmainz.cs.semgis.arqextension.test.point.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import de.hsmainz.cs.semgis.arqextension.point.attribute.Angle;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class AngleTest {

	public static final String testPoint1="POINT(25, 45)";
	
	public static final String testPoint2="POINT(75, 100)";

	@Test
	public void testAngle() {
        Angle instance=new Angle();
        NodeValue geom1 = GeometryWrapperFactory.createPoint(new Coordinate(25.,45.), WKTDatatype.URI).asNodeValue();
        NodeValue geom2 = GeometryWrapperFactory.createPoint(new Coordinate(75.,100.), WKTDatatype.URI).asNodeValue();
        NodeValue geom3 = GeometryWrapperFactory.createPoint(new Coordinate(50.,80.), WKTDatatype.URI).asNodeValue();
        NodeValue expResult = NodeValue.makeDouble(0.15824032445087835);
        NodeValue result = instance.exec(geom1,geom2,geom3);
        assertEquals(expResult, result);
	}
	
}
