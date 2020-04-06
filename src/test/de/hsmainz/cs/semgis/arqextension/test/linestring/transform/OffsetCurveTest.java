package de.hsmainz.cs.semgis.arqextension.test.linestring.transform;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import de.hsmainz.cs.semgis.arqextension.linestring.transform.OffsetCurve;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class OffsetCurveTest {

	public static final String testLineString="LINESTRING(1 2,1 10)";
	
	public static final String result="LINESTRING(-9 2, -9 10)";
	
	@Test
	public void testOffsetCurve() {
        NodeValue geometryLiteral = NodeValue.makeNode(testLineString, WKTDatatype.INSTANCE);
        OffsetCurve instance=new OffsetCurve();
        List<Coordinate> coords=new LinkedList<Coordinate>();
        coords.add(new Coordinate(1,10));
        coords.add(new Coordinate(1,2));
        NodeValue expResult = NodeValue.makeNode(result, WKTDatatype.INSTANCE);
        NodeValue result = instance.exec(geometryLiteral,NodeValue.makeDouble(10.));
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
}
