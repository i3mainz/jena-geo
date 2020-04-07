package de.hsmainz.cs.semgis.arqextension.test.unit;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.unit.MeterToNauticalMile;

public class MeterToNauticalMileTest {

	@Test
	public void testMeterToNauticalMile() {
        NodeValue unitamount = NodeValue.makeDouble(1852);
		MeterToNauticalMile instance=new MeterToNauticalMile();
        NodeValue expResult = NodeValue.makeDouble(1);
        NodeValue result = instance.exec(unitamount);
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
}
