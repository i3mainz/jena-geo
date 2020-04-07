package de.hsmainz.cs.semgis.arqextension.test.unit;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.unit.MeterToFoot;

public class MeterToFootTest {
	
	@Test
	public void testInchToMeter() {
        NodeValue unitamount = NodeValue.makeDouble(1.);
        MeterToFoot instance=new MeterToFoot();
        NodeValue expResult = NodeValue.makeDouble(3.2808);
        NodeValue result = instance.exec(unitamount);
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
}
