package de.hsmainz.cs.semgis.arqextension.test.unit;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.unit.MeterToUSMile;

public class MeterToUSMileTest {

	@Test
	public void testMeterToUSMile() {
        NodeValue unitamount = NodeValue.makeDouble(1.);
        MeterToUSMile instance=new MeterToUSMile();
        NodeValue expResult = NodeValue.makeDouble(6.213699E-4);
        NodeValue result = instance.exec(unitamount);
        System.out.println(result);
        assertEquals(expResult, result);
	}
}
