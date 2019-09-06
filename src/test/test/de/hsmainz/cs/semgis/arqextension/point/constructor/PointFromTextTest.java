package test.de.hsmainz.cs.semgis.arqextension.point.constructor;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import de.hsmainz.cs.semgis.arqextension.point.constructor.PointFromText;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class PointFromTextTest {

	public static final String testPoint1="<http://www.opengis.net/def/crs/EPSG/0/4326> POINT(-71.1043443253471 42.3150676015829)";
	
	@Test
	public void testPointFromText() {
        PointFromText instance=new PointFromText();
        NodeValue expResult = GeometryWrapperFactory.createPoint(new Coordinate(-71.1043443253471,42.3150676015829), WKTDatatype.URI).asNodeValue();
        NodeValue result = instance.exec(NodeValue.makeString("<http://www.opengis.net/def/crs/EPSG/0/4326> POINT(-71.1043443253471 42.3150676015829)"));
        assertEquals(expResult, result);
	}
	
}
