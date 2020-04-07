package de.hsmainz.cs.semgis.arqextension.raster.constructor;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;

import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CovJSONDatatype;

public class RastFromCoverageJSON extends FunctionBase1 {
	
	@Override
	public NodeValue exec(NodeValue arg0) {
		String wkbstring=arg0.getString();
		return CovJSONDatatype.INSTANCE.read(wkbstring).asNodeValue();
	}
	
}
