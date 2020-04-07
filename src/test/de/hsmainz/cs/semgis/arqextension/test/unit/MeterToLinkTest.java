package de.hsmainz.cs.semgis.arqextension.test.unit;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.unit.MeterToLink;

public class MeterToLinkTest {

	@Test
	public void testInchToMeter() {
        NodeValue unitamount = NodeValue.makeDouble(1.);
        MeterToLink instance=new MeterToLink();
        NodeValue expResult = NodeValue.makeDouble(4.970969537898672);
        NodeValue result = instance.exec(unitamount);
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
}
