package test.de.hsmainz.cs.semgis.arqextension.linestring;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.linestring.IsClosed;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class IsRingTest {

	public static final String isRing="LINESTRING(0 0, 0 1, 1 1, 1 0, 0 0)";
	
	public static final String isNotRing="LINESTRING(0 0, 0 1, 1 0, 1 1, 0 0)";
	
	@Test
	public void testLineStringIsRing() {
        NodeValue geometryLiteral = NodeValue.makeNode(isRing, WKTDatatype.INSTANCE);
        IsClosed instance=new IsClosed();
        NodeValue expResult = NodeValue.makeNodeBoolean(true);
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}

	@Test
	public void testLineStringIsNotRing() {
        NodeValue geometryLiteral = NodeValue.makeNode(isNotRing, WKTDatatype.INSTANCE);
        IsClosed instance=new IsClosed();
        NodeValue expResult = NodeValue.makeNodeBoolean(false);
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
}
