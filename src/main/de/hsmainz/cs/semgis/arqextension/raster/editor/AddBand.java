package de.hsmainz.cs.semgis.arqextension.raster.editor;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.apache.sis.coverage.grid.GridCoverage;

import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;
/**
 * Returns a raster with the new band(s) of given type added with given initial value in the given index location. If no index is specified, the band is added to the end. 
 *
 */
public class AddBand extends FunctionBase2 {
	@Override
	public NodeValue exec(NodeValue v,NodeValue v2) {
		CoverageWrapper wrapper=CoverageWrapper.extract(v);
		GridCoverage raster=wrapper.getXYGeometry();
		raster.getSampleDimensions().add(arg0)
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
