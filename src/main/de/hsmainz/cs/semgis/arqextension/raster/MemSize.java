package de.hsmainz.cs.semgis.arqextension.raster;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.geotoolkit.coverage.grid.GridCoverage2D;

import io.github.galbiston.geosparql_jena.implementation.CoverageWrapper;

/**
 * Returns the amount of space (in bytes) the raster takes.
 *
 */
public class MemSize extends FunctionBase1 {


	@Override
	public NodeValue exec(NodeValue v) {
		CoverageWrapper wrapper=CoverageWrapper.extract(v);
		GridCoverage2D raster=wrapper.getXYGeometry();	
        return NodeValue.makeInteger(raster.getRenderedImage().getData().getDataBuffer().getSize());
	}

}
