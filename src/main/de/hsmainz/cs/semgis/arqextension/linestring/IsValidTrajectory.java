package de.hsmainz.cs.semgis.arqextension.linestring;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LineString;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

/**
 * Returns true if the geometry is a valid trajectory
 *
 */
public class IsValidTrajectory extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue arg0) {
		try {
			GeometryWrapper geometry = GeometryWrapper.extract(arg0);
			Geometry geom = geometry.getXYGeometry();

			if (geom instanceof LineString) {
				Double lastM = Double.MIN_VALUE;
				for (Coordinate coord : geom.getCoordinates()) {
					if (Double.isNaN(coord.getM()) || coord.getM() <= lastM) {
						return NodeValue.FALSE;
					} else {
						lastM = coord.getM();
					}
				}
				return NodeValue.TRUE;
			}
			return NodeValue.nvNothing;
		} catch (DatatypeFormatException ex) {
			throw new ExprEvalException(ex.getMessage(), ex);
		}
	}

}
