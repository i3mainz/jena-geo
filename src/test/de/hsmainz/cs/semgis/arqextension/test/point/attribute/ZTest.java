package de.hsmainz.cs.semgis.arqextension.test.point.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.point.attribute.Z;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class ZTest {

	public static final String testPoint="POINT ZM(1 2 3 4)";
	
	@Test
	public void testZ() {
        NodeValue geometryLiteral = NodeValue.makeNode(testPoint, WKTDatatype.INSTANCE);
        Z instance=new Z();
        NodeValue expResult = NodeValue.makeDouble(3);
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
}
