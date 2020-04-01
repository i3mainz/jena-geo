package de.hsmainz.cs.semgis.arqextension.test.geometry.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.geometry.attribute.GeometryType;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class GeometryTypeTest {

	public static final String testGeom="LINESTRING(743238 2967416,743238 2967450,743265 2967450, 743265.625 2967416,743238 2967416)";
	
	@Test
	public void testLineString() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeom, WKTDatatype.INSTANCE);
        GeometryType instance=new GeometryType();
        NodeValue expResult = NodeValue.makeString("LineString");
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}

}
