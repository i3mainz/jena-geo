package de.hsmainz.cs.semgis.arqextension.raster.attribute;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.geotoolkit.image.ImageWorker;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import de.hsmainz.cs.semgis.arqextension.util.Wrapper;
import io.github.galbiston.geosparql_jena.implementation.CoverageWrapper;

public class IsTranslucent extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
		Wrapper wrapper1=LiteralUtils.rasterOrVector(v);
		GridCoverage2D raster=((CoverageWrapper)wrapper1).getXYGeometry();
	    ImageWorker worker=new ImageWorker(raster.getRenderedImage());
		return NodeValue.makeBoolean(worker.isTranslucent());
	}

}
