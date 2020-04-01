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

import de.hsmainz.cs.semgis.arqextension.geometry.transform.Force4D;
import de.hsmainz.cs.semgis.arqextension.vocabulary.WKT;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class ForceCollectionTest {

	public static final String testGeom="POLYGON((0 0 1,0 5 1,5 0 1,0 0 1),(1 1 1,3 1 1,1 3 1,1 1 1))";
	
	@Test
	public void testForceCollection() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeom, WKTDatatype.INSTANCE);
        Force4D instance=new Force4D();
        List<Coordinate> coords=new LinkedList<Coordinate>();
        coords.add(new CoordinateXYZM(0,0,0,1));
        coords.add(new CoordinateXYZM(0,5,0,2));
        coords.add(new CoordinateXYZM(5,0,0,0));
        coords.add(new CoordinateXYZM(0,0,0,0));
        Polygon poly=(Polygon) GeometryWrapperFactory.createPolygon(coords, WKT.DATATYPE_URI).getXYGeometry();
        List<Geometry> polylist=new LinkedList<Geometry>();
        polylist.add(poly);
        //        POLYGON((0 0,0 5,5 0,0 0),(1 1,3 1,1 3,1 1))
        NodeValue expResult = GeometryWrapperFactory.createGeometryCollection(polylist, ""+poly.getSRID(), WKT.DATATYPE_URI).asNodeValue();
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
}
