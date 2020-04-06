package de.hsmainz.cs.semgis.arqextension.test.polygon.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.polygon.attribute.IsAcute;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class IsAcuteTest {

public static final String isocelesTriangle="POLYGON((8 2, 11 13, 2 6, 8 2))";
	
	public static final String notIsocelesTriangle="POLYGON((1 2, 3 4, 5 6, 1 2))";
	
	@Test
	public void testIsAcuteTrue() {
        NodeValue geometryLiteral = NodeValue.makeNode(isocelesTriangle, WKTDatatype.INSTANCE);
        IsAcute instance=new IsAcute();
        NodeValue expResult = NodeValue.TRUE;
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
	@Test
	public void testIsAcuteFalse() {
        NodeValue geometryLiteral = NodeValue.makeNode(notIsocelesTriangle, WKTDatatype.INSTANCE);
        IsAcute instance=new IsAcute();
        NodeValue expResult = NodeValue.FALSE;
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
}
