package de.hsmainz.cs.semgis.arqextension.test.geometry.exporter;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import de.hsmainz.cs.semgis.arqextension.geometry.exporter.AsGeoBuf;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class AsGeobufTest {

	public static final String geobufString="GAAiEAoOCgwIBBoIAAAAAgIAAAE=";
	
	public static final String testGeometry="POLYGON((0 0,0 1,1 1,1 0,0 0))";
	
	@Test
	public void testAsGeobuf() {
		List<Coordinate> coords=new LinkedList<Coordinate>();
		coords.add(new Coordinate(0.,0.));
		coords.add(new Coordinate(0.,1.));
		coords.add(new Coordinate(1.,1.));
		coords.add(new Coordinate(1.,0.));
		coords.add(new Coordinate(0.,0.));
        NodeValue geometryLiteral = GeometryWrapperFactory.createPolygon(coords,WKTDatatype.URI).asNodeValue();
        AsGeoBuf instance=new AsGeoBuf();
        NodeValue expResult = NodeValue.makeString("GAAiEAoOCgwIBBoIAAAAAgIAAAE=");
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
}
