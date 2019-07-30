package de.hsmainz.cs.semgis.arqextension.raster;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.geotoolkit.referencing.GeodeticCalculator;

import io.github.galbiston.geosparql_jena.implementation.CoverageWrapper;
/**
 * Returns the X component of the pixel width in units of coordinate reference system.
 *
 */
public class ScaleX extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
        CoverageWrapper wrapper=CoverageWrapper.extract(v);
		GridCoverage2D raster=wrapper.getXYGeometry();
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
