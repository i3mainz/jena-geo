package de.hsmainz.cs.semgis.arqextension.envelope;

import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

/**
 * Returns TRUE if A's bounding box overlaps or is below B's.
 *
 */
public class BBOXOverlapsBelow extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
        try {
            GeometryWrapper geom = GeometryWrapper.extract(v1);
            GeometryWrapper geom2 = GeometryWrapper.extract(v2);
			GeometryWrapper transGeom2 = geom2.transform(geom.getSRID());
			if(geom.getEnvelope().overlaps(transGeom2.getEnvelope())) {
				return NodeValue.TRUE;
			}
			if(geom.getEnvelope().getMaxY()<transGeom2.getEnvelope().getMinY()) {
				return NodeValue.TRUE;
			}
			return NodeValue.FALSE;
		} catch (MismatchedDimensionException | TransformException | FactoryException e) {
			// TODO Auto-generated catch block
			throw new ExprEvalException(e.getMessage(), e);
		}
	}

}
