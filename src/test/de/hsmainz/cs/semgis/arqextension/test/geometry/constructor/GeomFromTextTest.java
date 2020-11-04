package de.hsmainz.cs.semgis.arqextension.test.geometry.constructor;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.io.ParseException;

import de.hsmainz.cs.semgis.arqextension.geometry.constructor.GeomFromText;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class GeomFromTextTest {

	public static final String geoJsonTestGeom="POINT(48.23456 20.12345)";
	
	public static final String inputText="POINT(48.23456 20.12345)";
	
	@Test
	public void testGeomFromText() throws ParseException {
        GeomFromText instance=new GeomFromText();
        NodeValue expResult = NodeValue.makeNode(geoJsonTestGeom,WKTDatatype.INSTANCE);      
        NodeValue result = instance.exec(NodeValue.makeString(geoJsonTestGeom));
        assertEquals(expResult, result);
	}
	
}
