package test.de.hsmainz.cs.semgis.arqextension.linestring;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.linestring.IsClosed;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class IsClosedTest {

	public static final String notClosed="LINESTRING(0 0, 1 1)";
	
	public static final String isClosed="LINESTRING(0 0, 0 1, 1 1, 0 0)";
	
	public static final String mlNotClosed="MULTILINESTRING((0 0, 0 1, 1 1, 0 0),(0 0, 1 1))";
	
	public static final String pointClosed="POINT(0 0)";
	
	public static final String multiPointClosed="MULTIPOINT((0 0), (1 1))";
	
	
	@Test
	public void testLineStringNotClosed() {
        NodeValue geometryLiteral = NodeValue.makeNode(notClosed, WKTDatatype.INSTANCE);
        IsClosed instance=new IsClosed();
        NodeValue expResult = NodeValue.makeNodeBoolean(false);
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}

	@Test
	public void testLineStringClosed() {
        NodeValue geometryLiteral = NodeValue.makeNode(isClosed, WKTDatatype.INSTANCE);
        IsClosed instance=new IsClosed();
        NodeValue expResult = NodeValue.makeNodeBoolean(true);
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
	@Test
	public void testMultiLineStringNotClosed() {
        NodeValue geometryLiteral = NodeValue.makeNode(mlNotClosed, WKTDatatype.INSTANCE);
        IsClosed instance=new IsClosed();
        NodeValue expResult = NodeValue.makeNodeBoolean(false);
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}	

	@Test
	public void testpointClosed() {
        NodeValue geometryLiteral = NodeValue.makeNode(pointClosed, WKTDatatype.INSTANCE);
        IsClosed instance=new IsClosed();
        NodeValue expResult = NodeValue.makeNodeBoolean(true);
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}	

	@Test
	public void testmultiPointClosed() {
        NodeValue geometryLiteral = NodeValue.makeNode(multiPointClosed, WKTDatatype.INSTANCE);
        IsClosed instance=new IsClosed();
        NodeValue expResult = NodeValue.makeNodeBoolean(true);
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}	

}
