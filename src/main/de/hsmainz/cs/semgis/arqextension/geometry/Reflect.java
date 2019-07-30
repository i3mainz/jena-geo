package de.hsmainz.cs.semgis.arqextension.geometry;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.util.AffineTransformation;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

public class Reflect extends FunctionBase2{

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		try {
	        GeometryWrapper geometry = GeometryWrapper.extract(v1);
	        Geometry geom = geometry.getXYGeometry();
	        GeometryWrapper geometry2 = GeometryWrapper.extract(v2);
	        Geometry geom2 = geometry2.getXYGeometry();
	        if(geom2.getGeometryType().equalsIgnoreCase("LineString") || geom2.getGeometryType().equalsIgnoreCase("Point")) {
	        	AffineTransformation trans = new AffineTransformation();
	        	if(geom2.getGeometryType().equalsIgnoreCase("Point")) {
	        		trans.setToReflection(geom2.getCoordinates()[0].x, geom2.getCoordinates()[0].y);		        		
	        	}else {
	        		trans.setToReflection(geom2.getCoordinates()[0].x, geom2.getCoordinates()[0].y, geom2.getCoordinates()[geom2.getCoordinates().length-1].x, geom2.getCoordinates()[geom2.getCoordinates().length-1].y);	
	        	}
	        	return GeometryWrapperFactory.createGeometry(trans.transform(geom), geometry.getSrsURI(), geometry.getGeometryDatatypeURI()).asNodeValue();
	        }else {
	        	throw new ExprEvalException("Given second geometry does not represent a reflection point or reflection line");	
	        }
	    } catch (DatatypeFormatException ex) {
	        throw new ExprEvalException(ex.getMessage(), ex);
	    }
	}

}
