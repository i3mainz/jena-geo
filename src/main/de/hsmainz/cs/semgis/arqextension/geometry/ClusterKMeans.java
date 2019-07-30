package de.hsmainz.cs.semgis.arqextension.geometry;

import java.math.BigInteger;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

/**
 * Windowing function that returns integer id for the cluster each input geometry is in.
 *
 */
public class ClusterKMeans extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		GeometryWrapper geom1 = GeometryWrapper.extract(v1);
        BigInteger numberOfClusters=v2.getInteger();
        throw new UnsupportedOperationException("Not supported yet.");
	}

}
