package de.hsmainz.cs.semgis.arqextension.test.linestring.constructor;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import de.hsmainz.cs.semgis.arqextension.linestring.constructor.LineFromEncodedPolyline;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class LineFromEncodedPolylineTest {

	public static final String testGeometry="LINESTRING(-120.2 38.5,-120.95 40.7,-126.453 43.252)";
	
	/*@Test
	public void testLineFromEncodedPolyline() {
        LineFromEncodedPolyline instance=new LineFromEncodedPolyline();
        List<Coordinate> coords=new LinkedList<Coordinate>();
        coords.add(new Coordinate(-1232.00015, -120.2));
        coords.add(new Coordinate(-1229.80015, -120.95));
        coords.add(new Coordinate(-1227.24815, -126.453));
        NodeValue expResult = GeometryWrapperFactory.createLineString(coords,"http://www.opengis.net/def/crs/EPSG/0/4326", WKTDatatype.URI).asNodeValue();
        NodeValue result = instance.exec(NodeValue.makeString("|_p~iF~ps|U_ulLnnqC_mqNvxq`@"),NodeValue.makeInteger(5));
        assertEquals(expResult, result);
	}*/
	
}
