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
	
	@Test
	public void testAsEncodedPolyline() {
        LineFromEncodedPolyline instance=new LineFromEncodedPolyline();
        List<Coordinate> coords=new LinkedList<Coordinate>();
        coords.add(new Coordinate(-120.2,38.5));
        coords.add(new Coordinate(-120.95,40.7));
        coords.add(new Coordinate(-126.453,43.252));     
        NodeValue expResult = GeometryWrapperFactory.createLineString(coords, WKTDatatype.URI).asNodeValue();
        NodeValue result = instance.exec(NodeValue.makeString("|_p~iF~ps|U_ulLnnqC_mqNvxq`@"),NodeValue.makeInteger(5));
        assertEquals(expResult, result);
	}
	
}
