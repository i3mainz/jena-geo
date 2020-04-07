package de.hsmainz.cs.semgis.arqextension.test.unit;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.unit.MeterToYard;

public class MeterToYardTest {

	@Test
	public void testMeterToYard() {
        NodeValue unitamount = NodeValue.makeDouble(1000.);
        MeterToYard instance=new MeterToYard();
        NodeValue expResult = NodeValue.makeDouble(1093.6);
        NodeValue result = instance.exec(unitamount);
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
}
