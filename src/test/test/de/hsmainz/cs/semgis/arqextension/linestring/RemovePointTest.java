package test.de.hsmainz.cs.semgis.arqextension.linestring;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import de.hsmainz.cs.semgis.arqextension.linestring.RemovePoint;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class RemovePointTest {

	public static final String lineString="LINESTRING(0 0 1, 1 1 1,2 2 1)";
	
	@Test
	public void testRemovePoint() {
        NodeValue geometryLiteral = NodeValue.makeNode(lineString, WKTDatatype.INSTANCE);
        RemovePoint instance=new RemovePoint();
        List<Coordinate> coords=new LinkedList<Coordinate>();
        coords.add(new Coordinate(0,0,1));
        coords.add(new Coordinate(1,1,1));
        NodeValue expResult = GeometryWrapperFactory.createLineString(coords, WKTDatatype.URI).asNodeValue();
        NodeValue result = instance.exec(geometryLiteral,NodeValue.makeInteger(1));
        assertEquals(expResult, result);
	}
	
}
