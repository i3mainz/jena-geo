package de.hsmainz.cs.semgis.arqextension.test.geometry.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import de.hsmainz.cs.semgis.arqextension.geometry.attribute.Centroid;
import de.hsmainz.cs.semgis.arqextension.geometry.relation.EqualType;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.HexWKBRastDatatype;

public class CentroidTest extends SampleRasters {

	public static final String testGeometry="POLYGON ((0 0, 1 0, 1 1, 0.5 3.2e-4, 0 0))";

	@Test
	public void testCentroid() {
        Centroid instance=new Centroid();
        NodeValue geometryLiteral = NodeValue.makeNode(testGeometry, WKTDatatype.INSTANCE);
        NodeValue expResult = GeometryWrapperFactory.createPoint(new Coordinate(0.8331201364460079, 0.33322680311267455), WKTDatatype.URI).asNodeValue();
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
	@Test
	public void testCentroidRaster() {
		NodeValue covLiteral = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
		Centroid instance=new Centroid();
        NodeValue expResult = GeometryWrapperFactory.createPoint(new Coordinate(0.5, 0.5), WKTDatatype.URI).asNodeValue();
        NodeValue result = instance.exec(covLiteral);
        assertEquals(expResult, result);
	}
	
}
