package de.hsmainz.cs.semgis.arqextension.raster.attribute;

import java.awt.geom.AffineTransform;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.opengis.coverage.grid.GridCoverage;
import org.opengis.coverage.grid.GridGeometry;
import org.opengis.referencing.datum.PixelInCell;

import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

public class TranslateX extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
        CoverageWrapper wrapper=CoverageWrapper.extract(v);
		GridCoverage raster=wrapper.getXYGeometry();
		GridGeometry gridGeometry2D = raster.getGridGeometry();
        AffineTransform gridToWorld = (AffineTransform) gridGeometry2D.getGridToCRS();
        return NodeValue.makeDouble(gridToWorld.getTranslateX());
	}

}
