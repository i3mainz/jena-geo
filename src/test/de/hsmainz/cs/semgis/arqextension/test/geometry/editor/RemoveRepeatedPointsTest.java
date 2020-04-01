package de.hsmainz.cs.semgis.arqextension.test.geometry.editor;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

import de.hsmainz.cs.semgis.arqextension.geometry.editor.RemoveRepeatedPoints;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class RemoveRepeatedPointsTest {

public static final String testGeometry="LINESTRING(0 0, 1 1, 2 4, 1 1,6 8)";

public static final String testGeometry2="LINESTRING(0 0, 2 4,6 8)";

	@Test
	public void testRemoveRepeatedPoints() throws ParseException {
        RemoveRepeatedPoints instance=new RemoveRepeatedPoints();
        NodeValue tolerance=NodeValue.makeDouble(0.);
        WKTReader reader=new WKTReader();
        Geometry geom=reader.read(testGeometry);
        Geometry geom2=reader.read(testGeometry2);
        NodeValue input=GeometryWrapperFactory.createLineString(geom.getCoordinates(), WKTDatatype.URI).asNodeValue();
        NodeValue expResult = GeometryWrapperFactory.createLineString(geom2.getCoordinates(), WKTDatatype.URI).asNodeValue();
        NodeValue result = instance.exec(input,tolerance);
        assertEquals(expResult, result);
	}


}
