package test.de.hsmainz.cs.semgis.arqextension.polygon;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import de.hsmainz.cs.semgis.arqextension.polygon.Polygon;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class PolygonTest {

	public static final String testLineString="<http://www.opengis.net/def/crs/EPSG/0/4326>LINESTRING((-71.1776585052917 42.3902909739571,-71.1776820268866 42.3903701743239,-71.1776063012595 42.3903825660754,-71.1775826583081 42.3903033653531,-71.1776585052917 42.3902909739571))";
	
	@Test
	public void testLineFromText() {
        Polygon instance=new Polygon();
        List<Coordinate> coords=new LinkedList<Coordinate>();
        coords.add(new Coordinate(-71.1776585052917, 42.3902909739571));
        coords.add(new Coordinate(-71.1776820268866, 42.3903701743239));
        coords.add(new Coordinate(-71.1776063012595, 42.3903825660754));
        coords.add(new Coordinate(-71.1775826583081, 42.3903033653531));
        coords.add(new Coordinate(-71.1776585052917, 42.3902909739571));
        NodeValue expResult = GeometryWrapperFactory.createPolygon(coords, "<http://www.opengis.net/def/crs/EPSG/0/4326>",WKTDatatype.URI).asNodeValue();
        NodeValue result = instance.exec(NodeValue.makeString(testLineString),NodeValue.makeInteger(4326));
        assertEquals(expResult, result);
	}
	
}
