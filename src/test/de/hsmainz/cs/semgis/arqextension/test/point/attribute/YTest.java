package de.hsmainz.cs.semgis.arqextension.test.point.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.point.attribute.Y;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class YTest {

	public static final String testPoint="POINT(1 2 3 4)";
	
	@Test
	public void testY() {
        NodeValue geometryLiteral = NodeValue.makeNode(testPoint, WKTDatatype.INSTANCE);
        Y instance=new Y();
        NodeValue expResult = NodeValue.makeDouble(2);
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
}
