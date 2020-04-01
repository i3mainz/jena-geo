package de.hsmainz.cs.semgis.arqextension.test.geometry.exporter;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.geometry.exporter.AsBinary;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class AsBinaryTest {

	public static final String testGeometry="POLYGON((0 0,0 1,1 1,1 0,0 0))";
	
	@Test
	public void testAsBinary() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeometry, WKTDatatype.INSTANCE);
        AsBinary instance=new AsBinary();
        NodeValue expResult = NodeValue.makeString("\\001\\003\\000\\000\\000\\001\\000\\000\\000\\005\n" + 
        		"\\000\\000\\000\\000\\000\\000\\000\\000\\000\\000\n" + 
        		"\\000\\000\\000\\000\\000\\000\\000\\000\\000\\000\n" + 
        		"\\000\\000\\000\\000\\000\\000\\000\\000\\000\\000\n" + 
        		"\\000\\000\\000\\360?\\000\\000\\000\\000\\000\\000\n" + 
        		"\\360?\\000\\000\\000\\000\\000\\000\\360?\\000\\000\n" + 
        		"\\000\\000\\000\\000\\360?\\000\\000\\000\\000\\000\n" + 
        		"\\000\\000\\000\\000\\000\\000\\000\\000\\000\\000\\000\n" + 
        		"\\000\\000\\000\\000\\000\\000\\000\\000");
        NodeValue result = instance.exec(geometryLiteral,NodeValue.makeString("XDR"));
        assertEquals(expResult, result);
	}
	
}
