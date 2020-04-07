package de.hsmainz.cs.semgis.arqextension.test.unit;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.unit.MeterToInch;

public class MeterToInchTest {

	@Test
	public void testMeterToInch() {
        NodeValue unitamount = NodeValue.makeDouble(0.0254);
        MeterToInch instance=new MeterToInch();
        NodeValue expResult = NodeValue.makeDouble(1.);
        NodeValue result = instance.exec(unitamount);
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
}
