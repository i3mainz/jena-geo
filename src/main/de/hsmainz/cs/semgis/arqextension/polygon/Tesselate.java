package de.hsmainz.cs.semgis.arqextension.polygon;

import java.math.BigInteger;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

/**
 * Perform surface Tesselation of a polygon or polyhedralsurface and returns as a TIN or collection of TINS
 *
 */
public class Tesselate extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
		GeometryWrapper geom1 = GeometryWrapper.extract(v);
        throw new UnsupportedOperationException("Not supported yet.");
	}

}
