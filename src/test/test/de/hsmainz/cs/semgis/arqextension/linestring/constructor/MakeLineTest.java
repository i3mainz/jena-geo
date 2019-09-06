package test.de.hsmainz.cs.semgis.arqextension.linestring.constructor;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import de.hsmainz.cs.semgis.arqextension.linestring.constructor.MakeLine;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class MakeLineTest {

	public static final String testPoint1="POINT(-71.1043443253471, 42.3150676015829)";
	
	public static final String testPointM="POINT M(1, 2,1.5)";
	
	@Test
	public void testMakeLine1() {
        MakeLine instance=new MakeLine();
        NodeValue expResult = GeometryWrapperFactory.createPoint(new Coordinate(-71.1043443253471,42.3150676015829), WKTDatatype.URI).asNodeValue();
        List<NodeValue> inputlist=new LinkedList<NodeValue>();
        inputlist.add(GeometryWrapperFactory.createPoint(new Coordinate(-71.1043443253471, 42.3150676015829), WKTDatatype.URI).asNodeValue());
        inputlist.add(GeometryWrapperFactory.createPoint(new Coordinate(1., 2.), WKTDatatype.URI).asNodeValue());
        NodeValue result = instance.exec(inputlist);
        assertEquals(expResult, result);
	}
	
	
	
}
