package test.de.hsmainz.cs.semgis.arqextension.geometry;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import de.hsmainz.cs.semgis.arqextension.geometry.PointN;
import de.hsmainz.cs.semgis.arqextension.geometry.Points;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class PointsTest {

	public static final String testPolygon="POLYGON Z ((30 10 4,10 30 5,40 40 6, 30 10))";
	
	@Test
	public void testLineString() {
        NodeValue geometryLiteral = NodeValue.makeNode(testPolygon, WKTDatatype.INSTANCE);
        Points instance=new Points();
        NodeValue expResult = GeometryWrapperFactory.createMultiPoint(new Coordinate(2.,2.), WKTDatatype.URI).asNodeValue();
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
}
