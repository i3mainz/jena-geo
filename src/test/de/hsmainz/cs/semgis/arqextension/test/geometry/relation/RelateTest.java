package de.hsmainz.cs.semgis.arqextension.test.geometry.relation;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.geometry.relation.Relate;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class RelateTest {

public static final String isocelesTriangle="POLYGON((8 2, 11 13, 2 6, 8 2))";
	
	public static final String notIsocelesTriangle="POLYGON((1 2, 3 4, 5 6, 1 2))";
	
	@Test
	public void testRelate() {
        NodeValue geometryLiteral = NodeValue.makeNode(isocelesTriangle, WKTDatatype.INSTANCE);
        NodeValue geometryLiteral2 = NodeValue.makeNode(notIsocelesTriangle, WKTDatatype.INSTANCE);
        Relate instance=new Relate();
        NodeValue expResult = NodeValue.makeString("212101212");
        NodeValue result = instance.exec(geometryLiteral,geometryLiteral2);
        assertEquals(expResult, result);
	}
	
}
