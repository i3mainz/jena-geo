package de.hsmainz.cs.semgis.arqextension.linestring;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

/**
 * Returns one or more points interpolated along a line.
 *
 */
public class LineInterpolatePoints extends FunctionBase3 {

	@Override
	public NodeValue exec(NodeValue arg0, NodeValue arg1, NodeValue arg2) {
		GeometryWrapper geom1 = GeometryWrapper.extract(arg0);
        Double a_fraction = arg1.getDouble();
        Boolean repeat=arg2.getBoolean();
    	throw new UnsupportedOperationException("Not supported yet.");
	}

}
