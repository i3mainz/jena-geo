package de.hsmainz.cs.semgis.arqextension.test.unit;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.unit.DecimeterToMeter;

public class DecimeterToMeterTest {

	@Test
	public void testCentimeterToMeter() {
        NodeValue unitamount = NodeValue.makeDouble(1.);
        DecimeterToMeter instance=new DecimeterToMeter();
        NodeValue expResult = NodeValue.makeDouble(0.1);
        NodeValue result = instance.exec(unitamount);
        System.out.println(result);
        assertEquals(expResult, result);
	}

}
