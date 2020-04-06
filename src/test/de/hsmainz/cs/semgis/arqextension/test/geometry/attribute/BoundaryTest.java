package de.hsmainz.cs.semgis.arqextension.test.geometry.attribute;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import de.hsmainz.cs.semgis.arqextension.geometry.attribute.Boundary;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class BoundaryTest {

	public static final String testPolygon="POLYGON ((0 0, 1 0, 1 1, 0.5 3.2e-4, 0 0))";
	
	public static final String result="LINESTRING(0 0, 1 0, 1 1, 0.5 3.2E-4, 0 0)";
	
	@Test
	public void testBoundingDiagonal() {
        NodeValue geometryLiteral = NodeValue.makeNode(testPolygon, WKTDatatype.INSTANCE);
        Boundary instance=new Boundary();
        List<Coordinate> coords=new LinkedList<Coordinate>();
        coords.add(new Coordinate(0,0));
        coords.add(new Coordinate(1,1));
        NodeValue expResult = NodeValue.makeNode(result, WKTDatatype.INSTANCE);
        NodeValue result = instance.exec(geometryLiteral);
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
}
