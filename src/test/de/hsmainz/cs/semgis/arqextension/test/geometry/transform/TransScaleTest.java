package de.hsmainz.cs.semgis.arqextension.test.geometry.transform;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.geometry.transform.TransScale;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class TransScaleTest {

	public static final String testLineString="LINESTRING(1 2,1 10)";
	
	public static final String testLineStringRes="LINESTRING Z(6 8, 6 24)";
	
	@Test
	public void testTransScale() {
        NodeValue geometryLiteral = NodeValue.makeNode(testLineString, WKTDatatype.INSTANCE);
        TransScale instance=new TransScale();
        NodeValue scale1=NodeValue.makeInteger(2);
        NodeValue scale2=NodeValue.makeInteger(2);
        NodeValue transx=NodeValue.makeInteger(2);
        NodeValue transy=NodeValue.makeInteger(2);
        List<NodeValue> vals=new LinkedList<NodeValue>();
        vals.add(geometryLiteral);
        vals.add(scale1);
        vals.add(scale2);
        vals.add(transx);
        vals.add(transy);
        NodeValue expResult = NodeValue.makeNode(testLineStringRes, WKTDatatype.INSTANCE);
        NodeValue result = instance.exec(vals);
        assertEquals(expResult, result);
	}
	
}
