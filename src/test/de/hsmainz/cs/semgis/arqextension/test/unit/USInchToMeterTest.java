package de.hsmainz.cs.semgis.arqextension.test.unit;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.unit.USInchToMeter;

public class USInchToMeterTest {

	@Test
	public void testUSInchToMeter() {
        NodeValue unitamount = NodeValue.makeDouble(1.);
        USInchToMeter instance=new USInchToMeter();
        NodeValue expResult = NodeValue.makeDouble(0.0254000508);
        NodeValue result = instance.exec(unitamount);
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
}
