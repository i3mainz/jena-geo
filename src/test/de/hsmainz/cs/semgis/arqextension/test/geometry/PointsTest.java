package de.hsmainz.cs.semgis.arqextension.test.geometry;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.Collections;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import de.hsmainz.cs.semgis.arqextension.geometry.attribute.PointN;
import de.hsmainz.cs.semgis.arqextension.geometry.transform.Points;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;
import kotlin.collections.CollectionsKt;

public class PointsTest {

	public static final String testPolygon="POLYGON Z ((30 10 4,10 30 5,40 40 6, 30 10))";
	
	@Test
	public void testLineString() {
        NodeValue geometryLiteral = NodeValue.makeNode(testPolygon, WKTDatatype.INSTANCE);
        Points instance=new Points();
        NodeValue expResult = GeometryWrapperFactory.createMultiPoint(Collections.singletonList(new Coordinate(2.,2.)), WKTDatatype.URI).asNodeValue();
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
}
