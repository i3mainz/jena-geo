package de.hsmainz.cs.semgis.arqextension.test.geometry.exporter;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.geometry.exporter.AsX3D;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class AsX3DTest {

	public static final String testGeometry="POINT(49.9928617 8.2472526)";
	
	@Test
	public void testAsX3D() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeometry, WKTDatatype.INSTANCE);
        AsX3D instance=new AsX3D();
        NodeValue arg1=NodeValue.makeInteger(1);
        NodeValue arg2=NodeValue.makeInteger(1);
        NodeValue expResult = NodeValue.makeString("<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE X3D PUBLIC \"ISO//Web3D//DTD X3D 3.2//EN\"http://www.web3d.org/specifications/x3d-3.2.dtd\"><Scene><Shape><IndexedFaceSet coordIndex=\"0</IndexedFaceSet><Coordinate point=\"49.9928617 8.2472526\"/></Shape></Scene></X3D>");
        NodeValue result = instance.exec(geometryLiteral,arg1,arg2);
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
}
