package de.hsmainz.cs.semgis.arqextension.test.unit;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.unit.MeterToUSInch;

public class MeterToUSInchTest {

	@Test
	public void testMeterToUSInch() {
        NodeValue unitamount = NodeValue.makeDouble(1.);
        MeterToUSInch instance=new MeterToUSInch();
        NodeValue expResult = NodeValue.makeDouble(39.37);
        NodeValue result = instance.exec(unitamount);
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
}
