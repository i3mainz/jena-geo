package de.hsmainz.cs.semgis.arqextension.raster;

import java.util.List;

import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.apache.jena.sparql.function.FunctionEnv;
import org.geotoolkit.coverage.grid.GridCoverage2D;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

/**
 * Returns the rotation of the raster in radian.
 *
 */
public class Rotation extends FunctionBase1{

	@Override
	public NodeValue exec(NodeValue v) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
