package de.hsmainz.cs.semgis.arqextension.test.geometry.transform;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateXYZM;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Polygon;

import de.hsmainz.cs.semgis.arqextension.geometry.transform.PrecisionReducer;
import de.hsmainz.cs.semgis.arqextension.vocabulary.WKT;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class PrecisionReducerTest {

public static final String testGeom="POINT(0.3424 0.3424)";

public static final String resultGeom="POINT(0.34 0.34)";

	@Test
	public void testPrecisionReducer() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeom, WKTDatatype.INSTANCE);
        PrecisionReducer instance=new PrecisionReducer();
        List<Coordinate> coords=new LinkedList<Coordinate>();
        coords.add(new Coordinate(0.34,0.34));
        NodeValue expResult = GeometryWrapperFactory.createPoint(coords.get(0), WKTDatatype.URI).asNodeValue();
        NodeValue result = instance.exec(geometryLiteral,NodeValue.makeDouble(2));
        assertEquals(expResult, result);
	}
	
}
