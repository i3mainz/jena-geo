package de.hsmainz.cs.semgis.arqextension.test.unit;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.unit.MillimeterToMeter;

public class MillimeterToMeterTest {

	@Test
	public void testMillimeterToMeter() {
        NodeValue unitamount = NodeValue.makeDouble(1000.);
        MillimeterToMeter instance=new MillimeterToMeter();
        NodeValue expResult = NodeValue.makeDouble(1.);
        NodeValue result = instance.exec(unitamount);
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
}
