package de.hsmainz.cs.semgis.arqextension.test.unit;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.unit.MileToMeter;

public class MileToMeterTest {

	@Test
	public void testMileToMeter() {
        NodeValue unitamount = NodeValue.makeDouble(1.);
        MileToMeter instance=new MileToMeter();
        NodeValue expResult = NodeValue.makeDouble(1609.3470878864446);
        NodeValue result = instance.exec(unitamount);
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
}
