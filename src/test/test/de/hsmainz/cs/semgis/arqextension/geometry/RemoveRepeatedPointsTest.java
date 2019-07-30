package test.de.hsmainz.cs.semgis.arqextension.geometry;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import de.hsmainz.cs.semgis.arqextension.geometry.RemoveRepeatedPoints;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class RemoveRepeatedPointsTest {

	public static final String testGeometry="LINESTRING((0 0,0 1,1 1,1 0,0 0))";

	@Test
	public void testRemoveRepeatedPoints() {
		List<Coordinate> coords=new LinkedList<Coordinate>();
		coords.add(new Coordinate(0.,0.));
		coords.add(new Coordinate(0.,1.));
		coords.add(new Coordinate(1.,1.));
		coords.add(new Coordinate(1.,0.));
		coords.add(new Coordinate(0.,0.));		
        NodeValue geometryLiteral = GeometryWrapperFactory.createLineString(coords,WKTDatatype.URI).asNodeValue();
        RemoveRepeatedPoints instance=new RemoveRepeatedPoints();
		coords=new LinkedList<Coordinate>();
		coords.add(new Coordinate(0.,1.));
		coords.add(new Coordinate(1.,1.));
		coords.add(new Coordinate(1.,0.));
        NodeValue expResult = GeometryWrapperFactory.createLineString(coords, WKTDatatype.URI).asNodeValue();
        NodeValue result = instance.exec(geometryLiteral,NodeValue.makeDouble(0));
        assertEquals(expResult, result);
	}
	
}
