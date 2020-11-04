package de.hsmainz.cs.semgis.arqextension.test.linestring.constructor;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import de.hsmainz.cs.semgis.arqextension.linestring.constructor.LineFromMultiPoint;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class LineFromMultiPointTest {

	public static final String testMultiPoint="MULTIPOINT Z(1 2 3, 4 5 6, 7 8 9)";
	
	/*@Test
	public void testLineFromMultiPoint() {
        LineFromMultiPoint instance=new LineFromMultiPoint();
        List<Coordinate> coords=new LinkedList<Coordinate>();
        coords.add(new Coordinate(1.,2.,3.));
        coords.add(new Coordinate(4.,5.,6.));
        coords.add(new Coordinate(7.,8.,9.));
        NodeValue geom1 = GeometryWrapperFactory.createMultiPoint(coords, WKTDatatype.URI).asNodeValue();
        NodeValue expResult = GeometryWrapperFactory.createLineString(coords, WKTDatatype.URI).asNodeValue();
        NodeValue result = instance.exec(geom1);
        assertEquals(expResult, result);
	}*/
	
}
