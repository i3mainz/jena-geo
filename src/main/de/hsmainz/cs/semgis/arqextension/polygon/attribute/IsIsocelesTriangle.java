package de.hsmainz.cs.semgis.arqextension.polygon.attribute;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Polygon;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class IsIsocelesTriangle extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
        try {
            GeometryWrapper geometry = GeometryWrapper.extract(v);
            Geometry geom = geometry.getParsingGeometry();
            if (geom instanceof Polygon) {
            	if(geom.getCoordinates().length==4) {
            		Coordinate p0=geom.getCoordinates()[0];
            		Coordinate p1=geom.getCoordinates()[1];
            		Coordinate p2=geom.getCoordinates()[2];
            		double len0 = p1.distance(p2);
            	    double len1 = p0.distance(p2);
            	    double len2 = p0.distance(p1);
            	    if (len0 == len1 || len1 == len2 || len2 == len0)
            	        return NodeValue.TRUE;
            	    else
            	        return NodeValue.FALSE;
            	}
            }
            return NodeValue.FALSE;
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}
}
