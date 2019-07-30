package test.de.hsmainz.cs.semgis.arqextension.geometry;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.geometry.attribute.NDims;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class NDimsTest {

	public static final String geom2D="POINT(1 1)";
	
	public static final String geom3D="POINT Z(1 1 2)";
	
	public static final String geomM="POINT M(1 1 0.5)";
	
	
	@Test
	public void test2DGeom() {
        NodeValue geometryLiteral = NodeValue.makeNode(geom2D, WKTDatatype.INSTANCE);
        NDims instance=new NDims();
        NodeValue expResult = NodeValue.makeInteger(2);
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
	@Test
	public void test3DGeom() {
        NodeValue geometryLiteral = NodeValue.makeNode(geom3D, WKTDatatype.INSTANCE);
        NDims instance=new NDims();
        NodeValue expResult = NodeValue.makeInteger(3);
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}

	@Test
	public void testMGeom() {
        NodeValue geometryLiteral = NodeValue.makeNode(geomM, WKTDatatype.INSTANCE);
        NDims instance=new NDims();
        NodeValue expResult = NodeValue.makeInteger(2);
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
	
}
