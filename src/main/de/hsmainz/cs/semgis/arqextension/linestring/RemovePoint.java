package de.hsmainz.cs.semgis.arqextension.linestring;

import java.math.BigInteger;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.LineString;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

/**
 * Remove point from a linestring.
 *
 */
public class RemovePoint extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue arg0, NodeValue arg1) {
		try {
			GeometryWrapper geom1 = GeometryWrapper.extract(arg0);
			BigInteger zerobasedposition = arg1.getInteger();
			if (geom1.getParsingGeometry() instanceof LineString) {
				Coordinate[] coords = geom1.getParsingGeometry().getCoordinates();
				Coordinate[] newcoords = coords;
				ArrayUtils.remove(newcoords, zerobasedposition.intValue());
				GeometryWrapper lineStringWrapper = GeometryWrapperFactory.createLineString(newcoords,
						"<http://www.opengis.net/def/crs/EPSG/0/" + geom1.getSRID() + ">", WKTDatatype.URI);
				return lineStringWrapper.asNodeValue();
			}
			throw new ExprEvalException("First argument is not a LineString", null);
		} catch (DatatypeFormatException ex) {
			throw new ExprEvalException(ex.getMessage(), ex);
		}
	}

}
