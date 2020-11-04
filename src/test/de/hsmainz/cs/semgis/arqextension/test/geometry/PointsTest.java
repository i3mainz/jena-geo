package de.hsmainz.cs.semgis.arqextension.test.geometry;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import de.hsmainz.cs.semgis.arqextension.geometry.attribute.PointN;
import de.hsmainz.cs.semgis.arqextension.geometry.transform.Points;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;
import kotlin.collections.CollectionsKt;

public class PointsTest {

	public static final String testPolygon="POLYGON Z ((30 10 4,10 30 5,40 40 6, 30 10 4))";
	
	@Test
	public void testPoints() {
        NodeValue geometryLiteral = NodeValue.makeNode(testPolygon, WKTDatatype.INSTANCE);
        Points instance=new Points();
        List<Coordinate> coords=new LinkedList<Coordinate>();
        coords.add(new Coordinate(30.,10.,4.));
        coords.add(new Coordinate(10.,30.,5.));
        coords.add(new Coordinate(40.,40.,6.));
        coords.add(new Coordinate(30.,10.,4.));
        NodeValue expResult = GeometryWrapperFactory.createMultiPoint(coords, WKTDatatype.URI).asNodeValue();
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
}
