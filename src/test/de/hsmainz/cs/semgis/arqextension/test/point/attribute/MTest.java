package de.hsmainz.cs.semgis.arqextension.test.point.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.point.attribute.M;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class MTest {

	public static final String testPoint="POINT ZM(1 2 3 4)";
	
	@Test
	public void testM() {
        NodeValue geometryLiteral = NodeValue.makeNode(testPoint, WKTDatatype.INSTANCE);
        M instance=new M();
        NodeValue expResult = NodeValue.makeDouble(4);
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
}
