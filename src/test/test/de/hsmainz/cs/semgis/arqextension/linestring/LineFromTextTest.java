package test.de.hsmainz.cs.semgis.arqextension.linestring;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import de.hsmainz.cs.semgis.arqextension.linestring.constructor.LineFromText;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class LineFromTextTest {

	public static final String testLineString="LINESTRING(1 2, 3 4)";
	
	@Test
	public void testLineFromText() {
        LineFromText instance=new LineFromText();
        List<Coordinate> coords=new LinkedList<Coordinate>();
        coords.add(new Coordinate(1.,2.));
        coords.add(new Coordinate(3.,4.));
        NodeValue expResult = GeometryWrapperFactory.createLineString(coords, WKTDatatype.URI).asNodeValue();
        NodeValue result = instance.exec(NodeValue.makeString(testLineString));
        assertEquals(expResult, result);
	}
}
