package de.hsmainz.cs.semgis.arqextension.test.geometry.exporter;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.geometry.exporter.AsTWKB;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class AsTWKBTest {

	public static final String testGeometry="POINT(49.9928617 8.2472526)";
	
	/*@Test
	public void testAsTWKB() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeometry, WKTDatatype.INSTANCE);
        AsTWKB instance=new AsTWKB();
        NodeValue expResult = NodeValue.makeString("�");
        NodeValue result = instance.exec(geometryLiteral,NodeValue.makeString(""));
        System.out.println(result);
        assertEquals(expResult, result);
	}*/

}
