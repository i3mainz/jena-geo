package de.hsmainz.cs.semgis.arqextension.test.unit;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.unit.ChainToMeter;

public class ChainToMeterTest {

	@Test
	public void testChainToMeter() {
        NodeValue unitamount = NodeValue.makeDouble(1.);
        ChainToMeter instance=new ChainToMeter();
        NodeValue expResult = NodeValue.makeDouble(0.04971);
        NodeValue result = instance.exec(unitamount);
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
}
