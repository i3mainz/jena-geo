package de.hsmainz.cs.semgis.arqextension.point;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase4;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.util.GeometricShapeFactory;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

/**
 * Is the point geometry inside the circle defined by center_x, center_y, radius
 *
 */
public class PointInsideCircle extends FunctionBase4 {

	@Override
	public NodeValue exec(NodeValue arg0, NodeValue v2, NodeValue v3, NodeValue v4) {
	       try {
	    	   GeometryWrapper geometry1 = GeometryWrapper.extract(arg0);
	    	   Geometry geom=geometry1.getParsingGeometry();
	    	   Double centerx=v2.getDouble();
	    	   Double centery=v3.getDouble();
	    	   Double radius=v4.getDouble();
	            if("POINT".equals(geom.getGeometryType().toUpperCase())){
	            	GeometricShapeFactory shapeFactory = new GeometricShapeFactory();
	                shapeFactory.setNumPoints(32);
	                shapeFactory.setCentre(new Coordinate(centerx, centery));
	                shapeFactory.setSize(radius * 2);
	                Polygon circle=shapeFactory.createCircle();
	                return NodeValue.makeNodeBoolean(circle.contains(geom));
	            }else {
	            	throw new ExprEvalException("First argument does not represent a point", null);
	            }
	            
	        } catch (DatatypeFormatException ex) {
	            throw new ExprEvalException(ex.getMessage(), ex);
	        }
	}

}
