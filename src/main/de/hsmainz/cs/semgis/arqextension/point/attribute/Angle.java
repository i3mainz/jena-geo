package de.hsmainz.cs.semgis.arqextension.point.attribute;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class Angle extends FunctionBase3 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2, NodeValue v3) {
	    GeometryWrapper pointgeometry = GeometryWrapper.extract(v1);
	    Geometry geom = pointgeometry.getXYGeometry();
	    GeometryWrapper pointgeometry2 = GeometryWrapper.extract(v2);
	    Geometry geom2 = pointgeometry2.getXYGeometry();
	    GeometryWrapper pointgeometry3 = GeometryWrapper.extract(v3);
	    Geometry geom3 = pointgeometry3.getXYGeometry();		
		Coordinate coord1,coord2,coord3;
		if("Point".equals(geom.getGeometryType())) {
			coord1=geom.getCoordinate();
		}else {
			coord1=geom.getCentroid().getCoordinate();
		}
		if("Point".equals(geom2.getGeometryType())) {
			coord2=geom2.getCoordinate();
		}else {
			coord2=geom2.getCentroid().getCoordinate();
		}
		if("Point".equals(geom3.getGeometryType())) {
			coord3=geom3.getCoordinate();
		}else {
			coord3=geom3.getCentroid().getCoordinate();
		}
		return NodeValue.makeDouble(findAngle(coord1,coord2,coord3));
	}

	
	public double findAngle(Coordinate a,Coordinate b, Coordinate c) {
	    double AB = Math.sqrt(Math.pow(b.x-a.x,2)+ Math.pow(b.y-a.y,2));    
	    double BC = Math.sqrt(Math.pow(b.x-c.x,2)+ Math.pow(b.y-c.y,2)); 
	    double AC = Math.sqrt(Math.pow(c.x-a.x,2)+ Math.pow(c.y-a.y,2));
	    return Math.acos((BC*BC+AB*AB-AC*AC)/(2*BC*AB));
	}
}
