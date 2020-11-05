package de.hsmainz.cs.semgis.arqextension.test.geometry.attribute;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import de.hsmainz.cs.semgis.arqextension.geometry.attribute.Area;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleRasters;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.HexWKBRastDatatype;

public class AreaTest extends SampleRasters {

	public static final String testPolygon="POLYGON((743238 2967416,743238 2967450,743265 2967450,743265.625 2967416,743238 2967416))";
	
	@Test
	public void testArea() {
        Area instance=new Area();
        List<Coordinate> coords=new LinkedList<Coordinate>();
        coords.add(new Coordinate(743238, 2967416));
        coords.add(new Coordinate(743238, 2967450));
        coords.add(new Coordinate(743265, 2967450));
        coords.add(new Coordinate(743265.625, 2967416));
        coords.add(new Coordinate(743238, 2967416));
        NodeValue geom2 = GeometryWrapperFactory.createPolygon(coords, WKTDatatype.URI).asNodeValue();
        NodeValue expResult = NodeValue.makeDouble(928.625);
        NodeValue result = instance.exec(geom2);
        assertEquals(expResult, result);
	}
	
	@Test
	public void testAreaRaster() {
		NodeValue covLiteral = NodeValue.makeNode(wkbString1, HexWKBRastDatatype.INSTANCE);
		Area instance=new Area();
        NodeValue expResult = NodeValue.makeDouble(4);
        NodeValue result = instance.exec(covLiteral);
        assertEquals(expResult, result);
	}

	
}
