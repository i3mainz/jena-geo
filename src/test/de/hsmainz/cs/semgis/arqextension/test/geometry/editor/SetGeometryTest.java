package de.hsmainz.cs.semgis.arqextension.test.geometry.editor;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.geometry.editor.SetGeometry;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class SetGeometryTest {

public static final String testGeom="LINESTRING(77.29 29.07,77.42 29.26,77.27 29.31,77.29 29.07)";
	
	public static final String testGeom2="GEOMETRYCOLLECTION(MULTIPOINT(-2 3 , -2 2),LINESTRING(5 5 ,10 10),POLYGON((-7 4.2,-7.1 5,-7.1 4.3,-7 4.2)))";
	
	public static final String result="GEOMETRYCOLLECTION(LINESTRING(77.29 29.07,77.42 29.26,77.27 29.31,77.29 29.07),LINESTRING(5 5 ,10 10),POLYGON((-7 4.2,-7.1 5,-7.1 4.3,-7 4.2)))";

	
	@Test
	public void testSetGeometry() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeom2, WKTDatatype.INSTANCE);
        NodeValue geometryLiteral2 = NodeValue.makeNode(testGeom, WKTDatatype.INSTANCE);
        NodeValue index = NodeValue.makeInteger(0);
        SetGeometry instance=new SetGeometry();
        NodeValue expResult = NodeValue.makeNode(result, WKTDatatype.INSTANCE);
        NodeValue result = instance.exec(geometryLiteral,geometryLiteral2,index);
        assertEquals(expResult, result);
	}
	
}
