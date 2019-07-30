package test.de.hsmainz.cs.semgis.arqextension.linestring;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import de.hsmainz.cs.semgis.arqextension.linestring.AddPoint;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class AddPointTest {

	public static final String lineString="LINESTRING(0 0 1, 1 1 1)";
	
	public static final String point="POINT(1, 2, 3)";
	
	@Test
	public void testAddPoint() {
        NodeValue geometryLiteral = NodeValue.makeNode(lineString, WKTDatatype.INSTANCE);
        NodeValue geometryLiteral2 = NodeValue.makeNode(point, WKTDatatype.INSTANCE);
        AddPoint instance=new AddPoint();
        List<Coordinate> coords=new LinkedList<Coordinate>();
        coords.add(new Coordinate(0,0,1));
        coords.add(new Coordinate(1,1,1));
        coords.add(new Coordinate(1,2,3));
        NodeValue expResult = GeometryWrapperFactory.createLineString(coords, WKTDatatype.URI).asNodeValue();
        NodeValue result = instance.exec(geometryLiteral2,geometryLiteral);
        assertEquals(expResult, result);
	}
	
}
