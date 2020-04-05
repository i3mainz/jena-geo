package de.hsmainz.cs.semgis.arqextension.polygon.attribute;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.Triangle;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class Circumcentre extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
        try {
            GeometryWrapper geometry = GeometryWrapper.extract(v);
            Geometry geom = geometry.getParsingGeometry();
            if (geom instanceof Polygon) {
            	if(geom.getCoordinates().length==3) {
            		Coordinate p0=geom.getCoordinates()[0];
            		Coordinate p1=geom.getCoordinates()[1];
            		Coordinate p2=geom.getCoordinates()[2];
            		Coordinate coord=Triangle.circumcentre(p0, p1, p2);
            		GeometryWrapper pointWrapper = GeometryWrapperFactory.createPoint(coord, geometry.getSrsURI(), WKTDatatype.URI);
                    return pointWrapper.asNodeValue();
            	}
            }
            return NodeValue.FALSE;
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

}
