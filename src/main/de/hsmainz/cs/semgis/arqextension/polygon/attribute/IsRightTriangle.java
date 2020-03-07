package de.hsmainz.cs.semgis.arqextension.polygon.attribute;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Polygon;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class IsRightTriangle extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
        try {
            GeometryWrapper geometry = GeometryWrapper.extract(v);
            Geometry geom = geometry.getXYGeometry();
            if (geom instanceof Polygon) {
            	if(geom.getCoordinates().length==3) {
            		if(isRightAngledTriangle(geom.getCoordinates())) {
            			return NodeValue.TRUE;
            		}
            	}
            }
            return NodeValue.FALSE;
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}
	
	public boolean isRightAngledTriangle(Coordinate[] coords){
		Coordinate a=coords[0];
		Coordinate b=coords[1];
		Coordinate c=coords[2];
	    Double lengthA = getLength(a,b);
	    Double lengthB = getLength(b,c);
	    Double lengthC = getLength(c,a);
	    return  Math.pow(lengthA, 2) + Math.pow(lengthB, 2) == Math.pow(lengthC, 2) ||
	            Math.pow(lengthA, 2) + Math.pow(lengthC, 2) == Math.pow(lengthB, 2) ||
	            Math.pow(lengthC, 2) + Math.pow(lengthB, 2) == Math.pow(lengthA, 2);
	}
	
	public double getLength(Coordinate point1 , Coordinate point2){
	    return Math.sqrt((point2.getX()-point1.getX())*(point2.getX()-point1.getX()) + (point2.getY()-point1.getY())*(point2.getY()-point1.getY()));
	}
	

}
