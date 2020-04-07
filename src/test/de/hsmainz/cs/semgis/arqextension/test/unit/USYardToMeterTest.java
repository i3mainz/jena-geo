package de.hsmainz.cs.semgis.arqextension.test.unit;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.unit.USYardToMeter;

public class USYardToMeterTest {

	@Test
	public void testUSYardToMeter() {
        NodeValue unitamount = NodeValue.makeDouble(1000.);
        USYardToMeter instance=new USYardToMeter();
        NodeValue expResult = NodeValue.makeDouble(914.0767824497257);
        NodeValue result = instance.exec(unitamount);
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
}
