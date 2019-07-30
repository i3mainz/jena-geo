package de.hsmainz.cs.semgis.arqextension.geometry;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

/**
 * Returns the measure at which points interpolated along two lines are closest. 
 *
 */
public class ClosestPointOfApproach extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue arg0, NodeValue arg1) {
        try {
            GeometryWrapper geom1 = GeometryWrapper.extract(arg0);
            GeometryWrapper geom2 = GeometryWrapper.extract(arg1);

            GeometryWrapper transGeom2 = geom2.transform(geom1.getSrsInfo());

            throw new UnsupportedOperationException("Not supported yet.");
        } catch (DatatypeFormatException | FactoryException | TransformException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

}
