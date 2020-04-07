package de.hsmainz.cs.semgis.arqextension.test.unit;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.unit.USFootToMeter;

public class USFootToMeterTest {

	@Test
	public void testUSFootToMeter() {
        NodeValue unitamount = NodeValue.makeDouble(1.);
        USFootToMeter instance=new USFootToMeter();
        NodeValue expResult = NodeValue.makeDouble(0.30480060991089725);
        NodeValue result = instance.exec(unitamount);
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
}
