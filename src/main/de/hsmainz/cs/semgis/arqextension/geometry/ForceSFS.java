package de.hsmainz.cs.semgis.arqextension.geometry;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

/**
 * Force the geometries to use SFS 1.1 geometry types only.
 *
 */
public class ForceSFS extends FunctionBase2{

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		GeometryWrapper geom1 = GeometryWrapper.extract(v1);
		String version = v2.getString();
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
