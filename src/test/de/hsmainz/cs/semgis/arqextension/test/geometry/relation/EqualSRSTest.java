package de.hsmainz.cs.semgis.arqextension.test.geometry.relation;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.geometry.relation.EqualSRS;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class EqualSRSTest {

	public static final String testGeom="LINESTRING(743238 2967416,743238 2967450,743265 2967450, 743265.625 2967416,743238 2967416)";

	public static final String testGeom2="MULTIPOINT (0 1, 1 0, 2 1)";
	
	public static final String testGeom3="<http://www.opengis.net/def/crs/EPSG/0/27700> MULTIPOINT (10 40, 40 30, 20 20, 30 10)";

	public static final String testGeom4="<http://www.opengis.net/def/crs/EPSG/0/4326> MULTIPOINT (10 40, 40 30, 20 20, 30 10)";
	
	@Test
	public void testEqualSRS() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeom3, WKTDatatype.INSTANCE);
        NodeValue geometryLiteral2 = NodeValue.makeNode(testGeom3, WKTDatatype.INSTANCE);
        EqualSRS instance=new EqualSRS();
        NodeValue expResult = NodeValue.TRUE;
        NodeValue result = instance.exec(geometryLiteral,geometryLiteral2);
        assertEquals(expResult, result);
	}
	
	@Test
	public void testNoSRS() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeom, WKTDatatype.INSTANCE);
        NodeValue geometryLiteral2 = NodeValue.makeNode(testGeom2, WKTDatatype.INSTANCE);
        EqualSRS instance=new EqualSRS();
        NodeValue expResult = NodeValue.TRUE;
        NodeValue result = instance.exec(geometryLiteral,geometryLiteral2);
        assertEquals(expResult, result);
	}
	
	@Test
	public void testNotEqualSRS() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeom3, WKTDatatype.INSTANCE);
        NodeValue geometryLiteral2 = NodeValue.makeNode(testGeom4, WKTDatatype.INSTANCE);
        EqualSRS instance=new EqualSRS();
        NodeValue expResult = NodeValue.FALSE;
        NodeValue result = instance.exec(geometryLiteral,geometryLiteral2);
        assertEquals(expResult, result);
	}

}
