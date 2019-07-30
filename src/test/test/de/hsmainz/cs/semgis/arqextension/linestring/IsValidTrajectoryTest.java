package test.de.hsmainz.cs.semgis.arqextension.linestring;

import static org.junit.Assert.assertEquals;

import org.apache.jena.sparql.expr.NodeValue;
import org.junit.jupiter.api.Test;

import de.hsmainz.cs.semgis.arqextension.linestring.IsValidTrajectory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class IsValidTrajectoryTest {

	public static final String testGeometryValid="LINESTRING M(0 0 1, 0 1 2)";

	public static final String testGeometryInvalid="LINESTRING M(0 0 1, 0 1 0)";
	
	@Test
	public void testValidTrajectoryTrue() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeometryValid, WKTDatatype.INSTANCE);
        IsValidTrajectory instance=new IsValidTrajectory();
        NodeValue expResult = NodeValue.makeBoolean(true);
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}

	@Test
	public void testValidTrajectoryFalse() {
        NodeValue geometryLiteral = NodeValue.makeNode(testGeometryInvalid, WKTDatatype.INSTANCE);
        IsValidTrajectory instance=new IsValidTrajectory();
        NodeValue expResult = NodeValue.makeBoolean(false);
        NodeValue result = instance.exec(geometryLiteral);
        assertEquals(expResult, result);
	}
	
}
