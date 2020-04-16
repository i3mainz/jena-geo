package de.hsmainz.cs.semgis.arqextension.raster.exporter;

import java.util.List;

import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.apache.jena.sparql.function.FunctionEnv;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; 

public class AsPNG extends FunctionBase1{

	@Override
	public NodeValue exec(NodeValue v) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
