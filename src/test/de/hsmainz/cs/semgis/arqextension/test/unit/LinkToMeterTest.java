package de.hsmainz.cs.semgis.arqextension.test.unit;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.unit.LinkToMeter;

public class LinkToMeterTest {

	@Test
	public void testlinkToMeter() {
        NodeValue unitamount = NodeValue.makeDouble(1.);
        LinkToMeter instance=new LinkToMeter();
        NodeValue expResult = NodeValue.makeDouble(0.201168);
        NodeValue result = instance.exec(unitamount);
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
}
