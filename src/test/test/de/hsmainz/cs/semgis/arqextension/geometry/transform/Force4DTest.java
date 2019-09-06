package test.de.hsmainz.cs.semgis.arqextension.geometry.transform;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateXYZM;

import de.hsmainz.cs.semgis.arqextension.geometry.transform.Force4D;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class Force4DTest {

	public static final String testLineString="LINESTRING((0 0 1,0 5 2,5 0 3,0 0 4))";
	
	@Test
	public void testForce4D() {
        NodeValue geometryLiteral = NodeValue.makeNode(testLineString, WKTDatatype.INSTANCE);
        Force4D instance=new Force4D();
        List<Coordinate> coords=new LinkedList<Coordinate>();
        coords.add(new CoordinateXYZM(0,0,0,1));
        coords.add(new CoordinateXYZM(0,5,0,2));
        coords.add(new CoordinateXYZM(5,0,0,0));
        coords.add(new CoordinateXYZM(0,0,0,0));
        //        POLYGON((0 0,0 5,5 0,0 0),(1 1,3 1,1 3,1 1))
        NodeValue expResult = GeometryWrapperFactory.createLineString(coords, WKTDatatype.URI).asNodeValue();
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
}
