package de.hsmainz.cs.semgis.arqextension.raster.exporter;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;

import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CovJSONDatatype;

public class AsCoverageJSON extends FunctionBase1{
		

		@Override
		public NodeValue exec(NodeValue v) {
			String result=CovJSONDatatype.INSTANCE.unparse(v);
			return NodeValue.makeString(result);
		}
	
}
