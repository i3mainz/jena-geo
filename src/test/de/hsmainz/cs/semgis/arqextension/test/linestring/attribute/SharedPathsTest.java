package de.hsmainz.cs.semgis.arqextension.test.linestring.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.linestring.attribute.SharedPaths;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class SharedPathsTest {

	public static final String isRing="LINESTRING(0 0, 0 1, 1 1, 1 0, 1 1, 0 0)";
	
	public static final String isRing2="LINESTRING(0 0, 0 1, 1 1, 1 2, 1 1, 0 0)";
	
	public static final String point="GEOMETRYCOLLECTION(MULTILINESTRING((0 0, 0 1), (0 1, 1 1), (1 1, 0 0)))";
	
	@Test
	public void testSharedPaths() {
        NodeValue geometryLiteral = NodeValue.makeNode(isRing, WKTDatatype.INSTANCE);
        NodeValue geometryLiteral2 = NodeValue.makeNode(isRing2, WKTDatatype.INSTANCE);
        SharedPaths instance=new SharedPaths();
        NodeValue expResult = NodeValue.makeNode(point, WKTDatatype.INSTANCE);
        NodeValue result = instance.exec(geometryLiteral,geometryLiteral2);
        System.out.println(result);
        assertEquals(expResult, result);
	}

	
}
