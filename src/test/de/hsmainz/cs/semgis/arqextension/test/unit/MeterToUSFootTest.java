package de.hsmainz.cs.semgis.arqextension.test.unit;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.unit.MeterToUSFoot;

public class MeterToUSFootTest {

	@Test
	public void testInchToUSFoot() {
        NodeValue unitamount = NodeValue.makeDouble(1.);
        MeterToUSFoot instance=new MeterToUSFoot();
        NodeValue expResult = NodeValue.makeDouble(3.28083333);
        NodeValue result = instance.exec(unitamount);
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
}
