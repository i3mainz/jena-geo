package de.hsmainz.cs.semgis.arqextension.linestring;

import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Point;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

import com.vividsolutions.jump.algorithm.LengthToPoint;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

/**
 * Returns a float between 0 and 1 representing the location of the closest point on LineString to the given Point, as a fraction of total 2d line length.
 *
 */
public class LineLocatePoint extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue arg0, NodeValue arg1) {
		GeometryWrapper geom1 = GeometryWrapper.extract(arg0);
        GeometryWrapper geom2 = GeometryWrapper.extract(arg1);
        try {
			GeometryWrapper transGeom2 = geom2.transform(geom1.getSrsInfo());
			if(geom1.getXYGeometry().getGeometryType().equalsIgnoreCase("LineString") && 
					transGeom2.getXYGeometry().getGeometryType().equalsIgnoreCase("Point")) {
				LineString line=(LineString) geom1.getXYGeometry();
				Point point=(Point) transGeom2.getXYGeometry();
				//Double minDistance=Double.MAX_VALUE;
				LengthToPoint ltop=new LengthToPoint(line, point.getCoordinate());
				return NodeValue.makeDouble(ltop.getLength()/line.getLength());
				/*Coordinate closestPoint=null;
				Integer index=-1;
				int j=0;
				for(Coordinate coord:line.getCoordinates()) {
					Double curdistance=coord.distance(point.getCoordinate());
					if(curdistance<minDistance) {
						minDistance=curdistance;
						closestPoint=coord;
						index=j;
					}
					j++;
				}
				Double relDistance=calculateRelativeDistanceFromStart(line, index);
				return NodeValue.makeDouble(relDistance);*/
			}
			throw new ExprEvalException("Input geometries were not a LineString and a Point");
				
		} catch (MismatchedDimensionException | TransformException | FactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ExprEvalException("An exception occured: "+e.getMessage());
		}
    	
	}
	
	public double calculateRelativeDistanceFromStart(Geometry geom,Integer index) {
		Double length=geom.getLength();
		Double distanceaddition=0.;
		int j=0;
		Coordinate oldcoord=null;
		for(Coordinate coord:geom.getCoordinates()) {
			if(oldcoord!=null && j<index) {
				distanceaddition+=coord.distance(oldcoord);
			}
			oldcoord=coord;
			j++;
		}
		return distanceaddition/length;
	}

}
