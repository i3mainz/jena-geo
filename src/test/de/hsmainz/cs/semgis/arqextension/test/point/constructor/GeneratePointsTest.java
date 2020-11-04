package de.hsmainz.cs.semgis.arqextension.test.point.constructor;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.CoordinateXYM;

import de.hsmainz.cs.semgis.arqextension.point.constructor.GeneratePoints;
import de.hsmainz.cs.semgis.arqextension.test.util.SampleGeometries;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class GeneratePointsTest {

	public static final String testPoint="POINT M(-71.1043443253471, 42.3150676015829, 10)";
	
	/*@Test
	public void testGeneratePoints() {
        GeneratePoints instance=new GeneratePoints();
        NodeValue expResult = GeometryWrapperFactory.createPoint(new CoordinateXYM(-71.1043443253471,42.3150676015829,10.), WKTDatatype.URI).asNodeValue();
        List<NodeValue> inputlist=new LinkedList<NodeValue>();
        inputlist.add(NodeValue.makeDouble(-71.1043443253471));
        inputlist.add(NodeValue.makeDouble(42.3150676015829));
        inputlist.add(NodeValue.makeDouble(10.));
        NodeValue result = instance.exec(inputlist);
        assertEquals(expResult, result);
	}*/

}
