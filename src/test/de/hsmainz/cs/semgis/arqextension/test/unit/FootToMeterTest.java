package de.hsmainz.cs.semgis.arqextension.test.unit;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.unit.FootToMeter;

public class FootToMeterTest {

	@Test
	public void testInchToMeter() {
        NodeValue unitamount = NodeValue.makeDouble(1.);
        FootToMeter instance=new FootToMeter();
        NodeValue expResult = NodeValue.makeDouble(0.30480370641307);
        NodeValue result = instance.exec(unitamount);
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
}
