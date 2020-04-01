package de.hsmainz.cs.semgis.arqextension.test.point.constructor;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateXYM;

import de.hsmainz.cs.semgis.arqextension.point.constructor.MakePoint;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class MakePointTest {

	public static final String testPoint1="POINT(-71.1043443253471, 42.3150676015829)";
	
	public static final String testPointM="POINT M(1, 2,1.5)";
	
	@Test
	public void testMakePoint1() {
        MakePoint instance=new MakePoint();
        NodeValue expResult = GeometryWrapperFactory.createPoint(new Coordinate(-71.1043443253471,42.3150676015829), WKTDatatype.URI).asNodeValue();
        List<NodeValue> inputlist=new LinkedList<NodeValue>();
        inputlist.add(NodeValue.makeDouble(-71.1043443253471));
        inputlist.add(NodeValue.makeDouble(42.3150676015829));
        NodeValue result = instance.exec(inputlist);
        assertEquals(expResult, result);
	}
	
	@Test
	public void testMakePointXYZ() {
        MakePoint instance=new MakePoint();
        NodeValue expResult = GeometryWrapperFactory.createPoint(new Coordinate(1.,2.,1.5), WKTDatatype.URI).asNodeValue();
        List<NodeValue> inputlist=new LinkedList<NodeValue>();
        inputlist.add(NodeValue.makeDouble(1.));
        inputlist.add(NodeValue.makeDouble(2.));
        inputlist.add(NodeValue.makeDouble(1.5));
        NodeValue result = instance.exec(inputlist);
        assertEquals(expResult, result);
	}
	
}
