package de.hsmainz.cs.semgis.arqextension.geometry.attribute;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.operation.valid.RepeatedPointTester;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; 
/**
 * Checks if a geometry has repeated points (TRUE) or not (FALSE)
 *
 */
public class HasRepeatedPoints extends FunctionBase1{

	@Override
	public NodeValue exec(NodeValue arg0) {
        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            Geometry geom = geometry.getParsingGeometry();
            RepeatedPointTester tester=new RepeatedPointTester();
            return NodeValue.makeNodeBoolean(tester.hasRepeatedPoint(geom));
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

}
