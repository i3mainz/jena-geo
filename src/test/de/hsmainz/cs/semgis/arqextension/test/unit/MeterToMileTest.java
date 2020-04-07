package de.hsmainz.cs.semgis.arqextension.test.unit;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.unit.MeterToMile;

public class MeterToMileTest {

	@Test
	public void testMeterToMile() {
        NodeValue unitamount = NodeValue.makeDouble(1000.);
        MeterToMile instance=new MeterToMile();
        NodeValue expResult = NodeValue.makeDouble(0.62137);
        NodeValue result = instance.exec(unitamount);
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
}
