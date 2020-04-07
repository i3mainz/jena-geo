package de.hsmainz.cs.semgis.arqextension.test.unit;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.unit.MeterToChain;

public class MeterToChainTest {

	@Test
	public void testMeterToChain() {
        NodeValue unitamount = NodeValue.makeDouble(1.);
        MeterToChain instance=new MeterToChain();
        NodeValue expResult = NodeValue.makeDouble(20.116676725005032);
        NodeValue result = instance.exec(unitamount);
        System.out.println(result);
        assertEquals(expResult, result);
	}
	
}
