package de.hsmainz.cs.semgis.arqextension.test.linestring.transform;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import de.hsmainz.cs.semgis.arqextension.linestring.transform.Segmentize;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class SegmentizeTest {

	public static final String isocelesTriangle="POLYGON((8 2, 11 13, 2 6, 8 2))";
	
	public static final String notIsocelesTriangle="POLYGON((1 2, 3 4, 5 6, 1 2))";
	
	@Test
	public void testSegmentize() {
        NodeValue geometryLiteral = NodeValue.makeNode(isocelesTriangle, WKTDatatype.INSTANCE);
        Segmentize instance=new Segmentize();
        List<Coordinate> coords=new LinkedList<Coordinate>();
        coords.add(new Coordinate(1,10));
        coords.add(new Coordinate(1,2));
        NodeValue expResult = NodeValue.makeNode(notIsocelesTriangle, WKTDatatype.INSTANCE);
        NodeValue result = instance.exec(geometryLiteral,NodeValue.makeDouble(10.));
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
}
