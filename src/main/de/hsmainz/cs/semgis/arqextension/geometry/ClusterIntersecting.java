package de.hsmainz.cs.semgis.arqextension.geometry;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; 
public class ClusterIntersecting extends FunctionBase1{

	@Override
	public NodeValue exec(NodeValue v) {
		GeometryWrapper geom1 = GeometryWrapper.extract(v);
        //This function expects a set of geometries. Needs to be implemented!
        throw new UnsupportedOperationException("Not supported yet.");
	}

}
