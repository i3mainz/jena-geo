package de.hsmainz.cs.semgis.arqextension.test.geometry.exporter;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import de.hsmainz.cs.semgis.arqextension.geometry.exporter.AsGeoHash;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class AsGeoHashTest {

	public static final String geoHashString="c0w3hf1s70w3hf1s70w3";
	
	public static final String testGeometry="POINT(-126,48)";
	
	@Test
	public void testAsGeobuf() {
        NodeValue geometryLiteral = GeometryWrapperFactory.createPoint(new Coordinate(-126.,48.),WKTDatatype.URI).asNodeValue();
        AsGeoHash instance=new AsGeoHash();
        NodeValue expResult = NodeValue.makeString("c0w3hf1s70w3hf1s70w3");
        NodeValue result = instance.exec(geometryLiteral,NodeValue.makeDouble(0.));
        assertEquals(expResult, result);
	}
	
}
