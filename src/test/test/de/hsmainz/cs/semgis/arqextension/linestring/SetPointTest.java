package test.de.hsmainz.cs.semgis.arqextension.linestring;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import de.hsmainz.cs.semgis.arqextension.linestring.SetPoint;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class SetPointTest {

	public static final String LineString2D="LINESTRING(0 1, 0 2)";
	
	public static final String point="POINT(1 1)";
	
	@Test
	public void testLineString2D() {
        List<Coordinate> coords=new LinkedList<Coordinate>();
        coords.add(new Coordinate(0,1));
        coords.add(new Coordinate(0,2));
        NodeValue geometryLiteral = GeometryWrapperFactory.createLineString(coords, WKTDatatype.URI).asNodeValue();
        NodeValue geometryLiteral2 = GeometryWrapperFactory.createPoint(new Coordinate(1.,1.), WKTDatatype.URI).asNodeValue();
        SetPoint instance=new SetPoint();
        coords=new LinkedList<Coordinate>();
        coords.add(new Coordinate(1,1));
        coords.add(new Coordinate(0,2));
        NodeValue expResult = GeometryWrapperFactory.createLineString(coords, WKTDatatype.URI).asNodeValue();
        NodeValue result = instance.exec(geometryLiteral,NodeValue.makeInteger(0),geometryLiteral2);
        assertEquals(expResult, result);
	}
	
}
