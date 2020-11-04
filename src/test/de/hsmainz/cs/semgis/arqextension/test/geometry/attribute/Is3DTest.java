package de.hsmainz.cs.semgis.arqextension.test.geometry.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.geometry.attribute.Is3D;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class Is3DTest {

	
	/*@Test
	public void testIs3DFalse() {
        NodeValue geometryLiteral = NodeValue.makeNode("<http://www.opengis.net/def/crs/EPSG/0/27700> MULTIPOINT (10 40, 40 30, 20 20, 30 10)", WKTDatatype.INSTANCE);
        Is3D instance=new Is3D();
        NodeValue expResult = NodeValue.makeNodeBoolean(false);
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}*/
	
	@Test
	public void testIs3DTrue() {
        NodeValue geometryLiteral = NodeValue.makeNode("<http://www.opengis.net/def/crs/EPSG/0/27700> MULTIPOINT Z (10 40 1, 40 30 2, 20 20 3, 30 10 4)", WKTDatatype.INSTANCE);
        Is3D instance=new Is3D();
        NodeValue expResult = NodeValue.makeNodeBoolean(true);
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}	
}
