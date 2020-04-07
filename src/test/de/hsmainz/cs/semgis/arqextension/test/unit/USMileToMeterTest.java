package de.hsmainz.cs.semgis.arqextension.test.unit;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.unit.USMileToMeter;

public class USMileToMeterTest {

	@Test
	public void testUSMileToMeter() {
        NodeValue unitamount = NodeValue.makeDouble(1.);
        USMileToMeter instance=new USMileToMeter();
        NodeValue expResult = NodeValue.makeDouble(1609.3473468862912);
        NodeValue result = instance.exec(unitamount);
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
}
