package de.hsmainz.cs.semgis.arqextension.raster;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.geotoolkit.coverage.grid.GridCoverage2D;
//import org.geotools.process.vector.VectorToRasterProcess;

public class AsRaster extends FunctionBase1 {


	@Override
	public NodeValue exec(NodeValue v) {
		//GridCoverage2D cov=VectorToRasterProcess.process(features, attribute, gridDim, bounds, covName, monitor)
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
