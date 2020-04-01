package de.hsmainz.cs.semgis.arqextension.geometry.attribute;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Geometry;
import org.opengis.geometry.coordinate.PolyhedralSurface;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

/**
 * Return the number of faces on a Polyhedral Surface. Will return null for non-polyhedral geometries.
 *
 */
public class NumPatches extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue arg0) {
        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            Geometry geom = geometry.getXYGeometry();
            return NodeValue.makeInteger(((PolyhedralSurface)geom).getPatches().size());
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

}
