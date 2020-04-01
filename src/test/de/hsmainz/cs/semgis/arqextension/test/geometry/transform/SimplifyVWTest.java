package de.hsmainz.cs.semgis.arqextension.test.geometry.transform;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import de.hsmainz.cs.semgis.arqextension.geometry.transform.SimplifyVW;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class SimplifyVWTest {

	public static final String testGeom="LINESTRING(5 2, 3 8, 6 20, 7 25, 10 10)";
	
	@Test
	public void testSimplifyVW() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeom, WKTDatatype.INSTANCE);
        SimplifyVW instance=new SimplifyVW();
        List<Coordinate> coords=new LinkedList<Coordinate>();
        coords.add(new Coordinate(5.,2.));
        coords.add(new Coordinate(7.,25.));
        coords.add(new Coordinate(10.,10.));
        NodeValue expResult = GeometryWrapperFactory.createLineString(coords, WKTDatatype.URI).asNodeValue();
        NodeValue result = instance.exec(geometryLiteral,NodeValue.makeInteger(30));
        assertEquals(expResult, result);
	}
	
	
}
