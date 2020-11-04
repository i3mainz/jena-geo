package de.hsmainz.cs.semgis.arqextension.test.geometry.transform;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.io.ParseException;

import de.hsmainz.cs.semgis.arqextension.geometry.transform.CollectionExtract;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class CollectionExtractTest {

public static final String testGeometry="GEOMETRYCOLLECTION(POINT(0 0))";

public static final String res="POINT(0 0)";
	
	/*@Test
	public void testCollectionExtract() throws ParseException {
        CollectionExtract instance=new CollectionExtract();
        NodeValue input=NodeValue.makeNode(testGeometry, WKTDatatype.INSTANCE);
        NodeValue expResult = NodeValue.makeNode(res, WKTDatatype.INSTANCE);
        NodeValue result = instance.exec(input,NodeValue.makeInteger(1));
        assertEquals(expResult, result);
	}*/
	
}
