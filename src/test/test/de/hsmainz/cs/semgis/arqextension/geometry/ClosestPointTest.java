package test.de.hsmainz.cs.semgis.arqextension.geometry;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import de.hsmainz.cs.semgis.arqextension.geometry.relation.ClosestPoint;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class ClosestPointTest {

	public static final String testPoint="POINT(100 100)";
	
	public static final String testLineString="LINESTRING (20 80, 98 190, 110 180, 50 75 )";
	
	@Test
	public void testClosestPoint() {
        ClosestPoint instance=new ClosestPoint();
        NodeValue geom1 = GeometryWrapperFactory.createPoint(new Coordinate(100.,100.), WKTDatatype.URI).asNodeValue();
        List<Coordinate> coords=new LinkedList<Coordinate>();
        coords.add(new Coordinate(20.,80.));
        coords.add(new Coordinate(98.,190.));
        coords.add(new Coordinate(110.,180.));
        coords.add(new Coordinate(50.,75.));
        NodeValue geom2 = GeometryWrapperFactory.createLineString(coords, WKTDatatype.URI).asNodeValue();
        NodeValue expResult = NodeValue.makeDouble(42.2736890060937);
        NodeValue result = instance.exec(geom1,geom2);
        assertEquals(geom1, result);
	}

}
