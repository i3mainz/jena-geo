package de.hsmainz.cs.semgis.arqextension.polygon;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Polygon;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

public class DumpRings extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue arg0) {

		try {
			GeometryWrapper geometry = GeometryWrapper.extract(arg0);
			Geometry geom = geometry.getParsingGeometry();
			if (geom.getGeometryType() != "POLYGON") {
				Polygon poly=(Polygon)geom;
				for (int i = 0; i < poly.getNumInteriorRing(); i++) {
					LineString n=poly.getInteriorRingN(i);
					Coordinate coord = coordinates[i];
					GeometryWrapper resWrapper = GeometryWrapperFactory.createPoint(coord, srsURI, geometryDatatypeURI);
					Literal resLiteral = resWrapper.asLiteral();
					String resString = resLiteral.toString();
					results.add(resString);
				}

				// Returning the list of space delimited literals. This is the same as
				// GROUP_CONCAT.
				// Correct splitting of results for use in query would need a Property Function.
				return NodeValue.makeString(String.join(" ", results));
			}
			return NodeValue.nvNothing;

		} catch (DatatypeFormatException ex) {
			throw new ExprEvalException(ex.getMessage(), ex);
		}
	}
}
