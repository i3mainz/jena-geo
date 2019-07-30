package de.hsmainz.cs.semgis.arqextension.raster;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import org.geotoolkit.coverage.grid.GridCoverage2D;

import io.github.galbiston.geosparql_jena.implementation.CoverageWrapper;

/**
 * Returns the centroid (point geometry) of the area represented by a pixel. 
 *
 */
public class PixelAsCentroid extends FunctionBase3 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2, NodeValue v3) {
		Integer longitude = v2.getInteger().intValue();
        Integer latitude = v3.getInteger().intValue();

        CoverageWrapper wrapper=CoverageWrapper.extract(v1);
		GridCoverage2D raster=wrapper.getXYGeometry();
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
