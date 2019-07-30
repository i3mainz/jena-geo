package test.de.hsmainz.cs.semgis.arqextension.geometry;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.geometry.OrderingEquals;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class OrderingEqualsTest {

	public static final String lineStringSmall="LINESTRING(0 0, 10 10)";
	
	public static final String lineStringSmallReverse="LINESTRING(10 10, 0 0)";
	
	public static final String lineStringMedium="LINESTRING(0 0, 5 5, 10 10)";
	
	public static final String lineStringMedium2="LINESTRING(0 0, 0 0, 10 10)";
	
	@Test
	public void testOrderingEquals1() {
        NodeValue geometryLiteral = NodeValue.makeNode(lineStringSmall, WKTDatatype.INSTANCE);
        NodeValue geometryLiteral2 = NodeValue.makeNode(lineStringMedium, WKTDatatype.INSTANCE);
        OrderingEquals instance=new OrderingEquals();
        NodeValue expResult = NodeValue.makeBoolean(false);
        NodeValue result = instance.exec(geometryLiteral,geometryLiteral2);
        assertEquals(expResult, result);
	}

	@Test
	public void testOrderingEquals2() {
        NodeValue geometryLiteral = NodeValue.makeNode(lineStringSmall, WKTDatatype.INSTANCE);
        NodeValue geometryLiteral2 = NodeValue.makeNode(lineStringMedium2, WKTDatatype.INSTANCE);
        OrderingEquals instance=new OrderingEquals();
        NodeValue expResult = NodeValue.makeBoolean(true);
        NodeValue result = instance.exec(geometryLiteral,geometryLiteral2);
        assertEquals(expResult, result);
	}

	@Test
	public void testOrderingEquals3() {
        NodeValue geometryLiteral = NodeValue.makeNode(lineStringSmallReverse, WKTDatatype.INSTANCE);
        NodeValue geometryLiteral2 = NodeValue.makeNode(lineStringMedium2, WKTDatatype.INSTANCE);
        OrderingEquals instance=new OrderingEquals();
        NodeValue expResult = NodeValue.makeBoolean(false);
        NodeValue result = instance.exec(geometryLiteral,geometryLiteral2);
        assertEquals(expResult, result);
	}
	
}
