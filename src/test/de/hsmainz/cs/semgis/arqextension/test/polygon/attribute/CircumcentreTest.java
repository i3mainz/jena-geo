package de.hsmainz.cs.semgis.arqextension.test.polygon.attribute;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import de.hsmainz.cs.semgis.arqextension.polygon.attribute.Circumcentre;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class CircumcentreTest {

	public static final String isocelesTriangle="POLYGON((8 2, 11 13, 2 6, 8 2))";
	
	public static final String notIsocelesTriangle="POLYGON((1 2, 3 4, 5 6, 1 2))";
	
	public static final String res1="POINT(7.666666666666667 8)";
	
	@Test
	public void testCircumcentre1() {
        NodeValue geometryLiteral = NodeValue.makeNode(isocelesTriangle, WKTDatatype.INSTANCE);
        Circumcentre instance=new Circumcentre();
        NodeValue expResult = GeometryWrapperFactory.createPoint(new Coordinate(7.666666666666667,8.), WKTDatatype.URI).asNodeValue();
        NodeValue result = instance.exec(geometryLiteral);
        System.out.println(result);
        assertEquals(expResult, result);
	}

}
