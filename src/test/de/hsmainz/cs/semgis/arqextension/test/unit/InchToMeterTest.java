package de.hsmainz.cs.semgis.arqextension.test.unit;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.unit.InchToMeter;

public class InchToMeterTest {

	@Test
	public void testInchToMeter() {
        NodeValue unitamount = NodeValue.makeDouble(1.);
        InchToMeter instance=new InchToMeter();
        NodeValue expResult = NodeValue.makeDouble(0.0254);
        NodeValue result = instance.exec(unitamount);
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
}
