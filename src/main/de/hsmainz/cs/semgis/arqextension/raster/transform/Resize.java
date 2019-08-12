package de.hsmainz.cs.semgis.arqextension.raster.transform;

import java.math.BigInteger;
import java.util.List;

import javax.media.jai.RenderedOp;

import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import org.apache.jena.sparql.function.FunctionEnv;
import org.apache.sis.coverage.grid.GridCoverage;

import io.github.galbiston.geosparql_jena.implementation.CoverageWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class Resize extends FunctionBase3{

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2, NodeValue v3) {
		CoverageWrapper wrapper=CoverageWrapper.extract(v1);
		GridCoverage raster=wrapper.getXYGeometry();
		BigInteger width=v2.getInteger();
		BigInteger height=v3.getInteger();
		raster.render(raster.getGridGeometry().getExtent()).
		ImageWorker w = new ImageWorker(img);
		// Setting RenderingHints
		w.setRenderingHints(img);
		// Setting ROI and NoData
		w.setROI(roi);
		w.setNoData(nodata);

		// Executing the operation
		w.rescale(scales, offsets);

		// Getting the result
		RenderedOp result = w.getRenderedImage();
		ImageWorker worker;
		worker.setROI(new ROI);
		CoverageUtilities.
		CoverageUtilities.setNoDataProperty(properties, container);
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
