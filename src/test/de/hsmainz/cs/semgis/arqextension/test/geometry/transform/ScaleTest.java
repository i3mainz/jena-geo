package de.hsmainz.cs.semgis.arqextension.test.geometry.transform;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import de.hsmainz.cs.semgis.arqextension.geometry.transform.Scale;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class ScaleTest {

	public static final String testLineString="LINESTRING(1 2,1 10)";
	
	/*@Test
	public void testScale() {
        NodeValue geometryLiteral = NodeValue.makeNode(testLineString, WKTDatatype.INSTANCE);
        Scale instance=new Scale();
        NodeValue scale1=NodeValue.makeInteger(2);
        NodeValue scale2=NodeValue.makeInteger(2);
        List<NodeValue> vals=new LinkedList<NodeValue>();
        vals.add(geometryLiteral);
        vals.add(scale1);
        vals.add(scale2);
        List<Coordinate> coords=new LinkedList<Coordinate>();
        coords.add(new Coordinate(2,4));
        coords.add(new Coordinate(2,20));
        NodeValue expResult = GeometryWrapperFactory.createLineString(coords, WKTDatatype.URI).asNodeValue();
        NodeValue result = instance.exec(vals);
        assertEquals(expResult, result);
	}*/
	
}
