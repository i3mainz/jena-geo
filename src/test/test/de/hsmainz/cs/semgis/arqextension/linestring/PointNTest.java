package test.de.hsmainz.cs.semgis.arqextension.linestring;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import de.hsmainz.cs.semgis.arqextension.geometry.PointN;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class PointNTest {

	public static final String lineString="LINESTRING(0 0, 1 1, 2 2)";
	
	public static final String circularLineString="CIRCULARSTRING(1 2, 3 2, 1 2)";
	
	public static final String lineString3D="LINESTRING(0 0 0, 1 1 1, 2 2 2)";
	
	@Test
	public void testLineString() {
        NodeValue geometryLiteral = NodeValue.makeNode(lineString, WKTDatatype.INSTANCE);
        PointN instance=new PointN();
        NodeValue expResult = GeometryWrapperFactory.createPoint(new Coordinate(2.,2.), WKTDatatype.URI).asNodeValue();
        NodeValue result = instance.exec(geometryLiteral,NodeValue.makeInteger(2));
        assertEquals(expResult, result);
	}
	
}
