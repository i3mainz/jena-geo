package de.hsmainz.cs.semgis.arqextension.geometry;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase4;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; 
public class LocateBetween extends FunctionBase4 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2, NodeValue v3, NodeValue v4) {
		GeometryWrapper geom1 = GeometryWrapper.extract(v1);
        Double measure_start=v2.getDouble();
        Double measure_end=v3.getDouble();
        Double offset=v4.getDouble();
        throw new UnsupportedOperationException("Not supported yet.");
	}

}
