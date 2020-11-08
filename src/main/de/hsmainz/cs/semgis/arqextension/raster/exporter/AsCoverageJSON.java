package de.hsmainz.cs.semgis.arqextension.raster.exporter;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.apache.sis.coverage.grid.GridCoverage;

import de.hsmainz.cs.semgis.arqextension.util.parsers.CoverageJsonWriter;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

public class AsCoverageJSON extends FunctionBase1{
		

		@Override
		public NodeValue exec(NodeValue v) {
			CoverageWrapper wrapper=CoverageWrapper.extract(v);
			GridCoverage raster=wrapper.getXYGeometry();	
			return NodeValue.makeString(CoverageJsonWriter.coverageToCovJSON(raster).toString(2));
		}
	
}
