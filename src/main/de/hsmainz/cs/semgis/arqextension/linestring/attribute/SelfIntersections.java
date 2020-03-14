package de.hsmainz.cs.semgis.arqextension.linestring.attribute;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

public class SelfIntersections extends FunctionBase1 {

    @Override
    public NodeValue exec(NodeValue arg0) {
        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            Geometry geom = geometry.getXYGeometry();
            	Set<Coordinate> coords=new HashSet<Coordinate>();
            	List<Coordinate> results=new LinkedList<Coordinate>();
            	for(Coordinate coord:geom.getCoordinates()) {
            		if(coords.contains(coord)) {
            			results.add(coord);
            		}
            		coords.add(coord);
            	}
            	return GeometryWrapperFactory.createMultiPoint(results, geometry.getSrsURI()).asNodeValue();
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
    }


}
