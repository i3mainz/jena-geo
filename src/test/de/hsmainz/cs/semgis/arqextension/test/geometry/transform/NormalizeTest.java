package de.hsmainz.cs.semgis.arqextension.test.geometry.transform;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import de.hsmainz.cs.semgis.arqextension.geometry.transform.Normalize;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class NormalizeTest {

public static final String testLineString="LINESTRING(1.42312321 2.34,1 10)";
	
	@Test
	public void testReverse() {
        NodeValue geometryLiteral = NodeValue.makeNode(testLineString, WKTDatatype.INSTANCE);
        Normalize instance=new Normalize();
        List<Coordinate> coords=new LinkedList<Coordinate>();
        coords.add(new Coordinate(1,10));
        coords.add(new Coordinate(1,2));
        NodeValue expResult = GeometryWrapperFactory.createLineString(coords, WKTDatatype.URI).asNodeValue();
        NodeValue result = instance.exec(geometryLiteral);
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
}
