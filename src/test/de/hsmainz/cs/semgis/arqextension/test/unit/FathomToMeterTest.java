package de.hsmainz.cs.semgis.arqextension.test.unit;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.unit.FathomToMeter;

public class FathomToMeterTest {

	@Test
	public void fathomToMeter() {
        NodeValue unitamount = NodeValue.makeDouble(1.);
        FathomToMeter instance=new FathomToMeter();
        NodeValue expResult = NodeValue.makeDouble(1.8287999998958315);
        NodeValue result = instance.exec(unitamount);
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
}
