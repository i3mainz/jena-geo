package de.hsmainz.cs.semgis.arqextension.test.unit;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.unit.YardToMeter;

public class YardToMeterTest {

	@Test
	public void testYardToMeter() {
        NodeValue unitamount = NodeValue.makeDouble(1000.);
        YardToMeter instance=new YardToMeter();
        NodeValue expResult = NodeValue.makeDouble(914.41111923921);
        NodeValue result = instance.exec(unitamount);
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
}
