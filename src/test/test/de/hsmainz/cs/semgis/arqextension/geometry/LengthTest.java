package test.de.hsmainz.cs.semgis.arqextension.geometry;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.geometry.attribute.Length;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class LengthTest {

	public static final String testGeom="LINESTRING(743238 2967416,743238 2967450,743265 2967450, 743265.625 2967416,743238 2967416)";
	
	@Test
	public void testLineString() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeom, WKTDatatype.INSTANCE);
        Length instance=new Length();
        NodeValue expResult = NodeValue.makeDouble(122.63074400009504);
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
}
