package de.hsmainz.cs.semgis.arqextension.test.unit;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.unit.MeterToUSYard;

public class MeterToUSYardTest {

	@Test
	public void testMeterToUSYard() {
        NodeValue unitamount = NodeValue.makeDouble(1000.);
        MeterToUSYard instance=new MeterToUSYard();
        NodeValue expResult = NodeValue.makeDouble(1094.0);
        NodeValue result = instance.exec(unitamount);
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
}
