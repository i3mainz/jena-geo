package de.hsmainz.cs.semgis.arqextension.test.unit;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.unit.MeterToFathom;

public class MeterToFathomTest {

	@Test
	public void testMeterToFathom() {
        NodeValue unitamount = NodeValue.makeDouble(1.);
        MeterToFathom instance=new MeterToFathom();
        NodeValue expResult = NodeValue.makeDouble(0.5468066492);
        NodeValue result = instance.exec(unitamount);
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
}
