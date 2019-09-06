package test.de.hsmainz.cs.semgis.arqextension.geometry.transform;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateXYM;

import de.hsmainz.cs.semgis.arqextension.geometry.transform.Force3DM;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class Force3DMTest {

	public static final String testPolygon="POLYGON((0 0 1,0 5 1,5 0 1,0 0 1))";
	
	public static final String testPolygon2="POLYGON((0 0,0 5,5 0,0 0))";
	
	@Test
	public void testForce3DM() {
        NodeValue geometryLiteral = NodeValue.makeNode(testPolygon, WKTDatatype.INSTANCE);
        Force3DM instance=new Force3DM();
        List<Coordinate> coords=new LinkedList<Coordinate>();
        coords.add(new CoordinateXYM(0,0,0));
        coords.add(new CoordinateXYM(0,5,0));
        coords.add(new CoordinateXYM(5,0,0));
        coords.add(new CoordinateXYM(0,0,0));
        //        POLYGON((0 0,0 5,5 0,0 0),(1 1,3 1,1 3,1 1))
        NodeValue expResult = GeometryWrapperFactory.createPolygon(coords, WKTDatatype.URI).asNodeValue();
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}

	@Test
	public void testForce3DM2() {
        NodeValue geometryLiteral = NodeValue.makeNode(testPolygon2, WKTDatatype.INSTANCE);
        Force3DM instance=new Force3DM();
        List<Coordinate> coords=new LinkedList<Coordinate>();
        coords.add(new CoordinateXYM(0,0,0));
        coords.add(new CoordinateXYM(0,5,0));
        coords.add(new CoordinateXYM(5,0,0));
        coords.add(new CoordinateXYM(0,0,0));
        //        POLYGON((0 0,0 5,5 0,0 0),(1 1,3 1,1 3,1 1))
        NodeValue expResult = GeometryWrapperFactory.createPolygon(coords, WKTDatatype.URI).asNodeValue();
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
}
