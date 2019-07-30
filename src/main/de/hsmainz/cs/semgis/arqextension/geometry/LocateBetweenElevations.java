package de.hsmainz.cs.semgis.arqextension.geometry;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

public class LocateBetweenElevations extends FunctionBase3 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2, NodeValue v3) {
		GeometryWrapper geom1 = GeometryWrapper.extract(v1);
        Double elevation_start=v2.getDouble();
        Double elevation_end=v3.getDouble();
        throw new UnsupportedOperationException("Not supported yet.");
	}

}
