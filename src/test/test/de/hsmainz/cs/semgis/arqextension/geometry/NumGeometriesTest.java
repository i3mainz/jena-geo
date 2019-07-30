package test.de.hsmainz.cs.semgis.arqextension.geometry;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.geometry.attribute.NumGeometries;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class NumGeometriesTest {

	public static final String testGeom="LINESTRING(77.29 29.07,77.42 29.26,77.27 29.31,77.29 29.07)";
	
	public static final String testGeom2="GEOMETRYCOLLECTION(MULTIPOINT(-2 3 , -2 2),LINESTRING(5 5 ,10 10),POLYGON((-7 4.2,-7.1 5,-7.1 4.3,-7 4.2)))";
	
	@Test
	public void testNumGeometries() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeom, WKTDatatype.INSTANCE);
        NumGeometries instance=new NumGeometries();
        NodeValue expResult = NodeValue.makeInteger(1);
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}

	@Test
	public void testNumGeometriesCollection() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeom2, WKTDatatype.INSTANCE);
        NumGeometries instance=new NumGeometries();
        NodeValue expResult = NodeValue.makeInteger(3);
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
}
