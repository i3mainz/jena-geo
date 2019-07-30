package test.de.hsmainz.cs.semgis.arqextension.envelope;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.io.ParseException;

import de.hsmainz.cs.semgis.arqextension.point.attribute.YMin;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class YMinTest {

	public static final String testLineString="LINESTRING(1 3 4, 5 6 7)";
	
	@Test
	public void testYMin() throws ParseException {
        YMin instance=new YMin();
        List<Coordinate> coords=new LinkedList<Coordinate>();
        coords.add(new Coordinate(1,3,4)); 
        coords.add(new Coordinate(5,6,7)); 
        NodeValue input=GeometryWrapperFactory.createLineString(coords, WKTDatatype.URI).asNodeValue();
        NodeValue expResult = NodeValue.makeDouble(3);
        NodeValue result = instance.exec(input);
        assertEquals(expResult, result);
	}
	
}
