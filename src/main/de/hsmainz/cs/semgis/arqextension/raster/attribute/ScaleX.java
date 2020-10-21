package de.hsmainz.cs.semgis.arqextension.raster.attribute;

import java.awt.geom.AffineTransform;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.geotoolkit.coverage.grid.GridGeometry2D;
import org.opengis.referencing.datum.PixelInCell;

import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;
/**
 * Returns the X component of the pixel width in units of coordinate reference system.
 *
 */
public class ScaleX extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
        CoverageWrapper wrapper=CoverageWrapper.extract(v);
		GridCoverage2D raster=wrapper.getXYGeometry();
		GridGeometry2D gridGeometry2D = raster.getGridGeometry();
        AffineTransform gridToWorld = (AffineTransform) gridGeometry2D.getGridToCRS(PixelInCell.CELL_CENTER);
        return NodeValue.makeDouble(gridToWorld.getScaleX());
	}

}
