package de.hsmainz.cs.semgis.arqextension.raster.attribute;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.opengis.coverage.CannotEvaluateException;

import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

public class PixelSize extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
		CoverageWrapper wrapper=CoverageWrapper.extract(v);
		GridCoverage2D raster=wrapper.getXYGeometry();
		try {
			return NodeValue.makeInteger(raster.getRenderedImage().getTileGridXOffset());
		} catch (CannotEvaluateException e) {
			return null;
		}

	}

}
