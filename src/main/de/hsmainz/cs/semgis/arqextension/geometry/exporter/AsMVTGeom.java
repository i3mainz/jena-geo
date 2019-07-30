package de.hsmainz.cs.semgis.arqextension.geometry.exporter;

import org.apache.jena.sparql.expr.NodeValue;

import io.github.galbiston.geosparql_jena.spatial.filter_functions.FunctionBase5;

/**
 * Transform a geometry into the coordinate space of a Mapbox Vector Tile.
 *
 */
public class AsMVTGeom extends FunctionBase5{

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2, NodeValue v3, NodeValue v4, NodeValue v5) {
		 throw new UnsupportedOperationException("Not supported yet.");
	}

}
