package de.hsmainz.cs.semgis.arqextension.test.point.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.point.attribute.T;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class TTest {

public static final String testPoint="POINT ZT(1 2 3 4)";
	
	/*@Test
	public void testT() {
        NodeValue geometryLiteral = NodeValue.makeNode(testPoint, WKTDatatype.INSTANCE);
        T instance=new T();
        NodeValue expResult = NodeValue.makeDouble(4);
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}*/
	
}
