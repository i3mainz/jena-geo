package de.hsmainz.cs.semgis.arqextension.test.geometry.relation;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.geometry.relation.RelateMatch;

public class RelateMatchTest {

	public static final String notIsocelesTriangle="POLYGON((1 2, 3 4, 5 6, 1 2))";
	
	@Test
	public void testRelateMatchTrue() {
        RelateMatch instance=new RelateMatch();
        NodeValue e1 = NodeValue.makeString("212101212");
        NodeValue e2 = NodeValue.makeString("212101212");
        NodeValue expResult = NodeValue.makeBoolean(true);
        NodeValue result = instance.exec(e1,e2);
        assertEquals(expResult, result);
	}
	
	@Test
	public void testRelateMatchFalse() {
        RelateMatch instance=new RelateMatch();
        NodeValue e1 = NodeValue.makeString("212FF1212");
        NodeValue e2 = NodeValue.makeString("212101212");
        NodeValue expResult = NodeValue.makeBoolean(false);
        NodeValue result = instance.exec(e1,e2);
        assertEquals(expResult, result);
	}
	
}
