package de.hsmainz.cs.semgis.arqextension.geometry;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

/**
 * Returns a version of the given geometry with duplicated points removed.
 *
 */
public class RemoveRepeatedPoints extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue arg0, NodeValue arg1) {
        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            Geometry geom=geometry.getXYGeometry();
            if(geom.getGeometryType().equalsIgnoreCase("Point")) {
            	return geometry.asNodeValue();
            }
            Double tolerance=arg1.getDouble();
            int i=0,j=0;
            boolean repeated=false;
            List<Coordinate> result=new LinkedList<Coordinate>();
            for(Coordinate coord:geom.getCoordinates()) {
            	j=0;
            	repeated=false;
            	for(Coordinate coord2:geom.getCoordinates()) {
            		if(i!=j && coord.equals2D(coord2, tolerance)) {
            			repeated=true;
            		}
            		j++;
            	}
        		if(!repeated) {
        			result.add(coord);
        		}
            	i++;
            }
            switch(geom.getGeometryType()) {
            case "MultiPoint": 
            	return GeometryWrapperFactory.createMultiPoint(result, geometry.getSrsURI(), WKTDatatype.URI).asNodeValue();
            case "Polygon":
            	return GeometryWrapperFactory.createPolygon(result, geometry.getSrsURI(), WKTDatatype.URI).asNodeValue();	
            case "LineString":
            	return GeometryWrapperFactory.createLineString(result, geometry.getSrsURI(), WKTDatatype.URI).asNodeValue();
            }
            throw new UnsupportedOperationException("Unsupported geometry type"); //To change body of generated methods, choose Tools | Templates.
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

}
