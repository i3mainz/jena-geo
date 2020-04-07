package de.hsmainz.cs.semgis.arqextension.test.unit;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.unit.KilometerToMeter;

public class KilometerToMeterTest {

	@Test
	public void testKilometerToMeter() {
        NodeValue unitamount = NodeValue.makeDouble(1.);
        KilometerToMeter instance=new KilometerToMeter();
        NodeValue expResult = NodeValue.makeDouble(1000.);
        NodeValue result = instance.exec(unitamount);
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
}
