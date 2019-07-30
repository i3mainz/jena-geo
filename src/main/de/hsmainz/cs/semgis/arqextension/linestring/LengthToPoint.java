package de.hsmainz.cs.semgis.arqextension.linestring;

import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Point;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class LengthToPoint extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		GeometryWrapper geom1 = GeometryWrapper.extract(v1);
        GeometryWrapper geom2 = GeometryWrapper.extract(v2);
    
			GeometryWrapper transGeom2;
			try {
				transGeom2 = geom2.transform(geom1.getSrsInfo());
				if(geom1.getXYGeometry().getGeometryType().equalsIgnoreCase("LineString") && 
						transGeom2.getXYGeometry().getGeometryType().equalsIgnoreCase("Point")) {
					LineString line=(LineString) geom1.getXYGeometry();
					Point point=(Point) transGeom2.getXYGeometry();
					//Double minDistance=Double.MAX_VALUE;
					com.vividsolutions.jump.algorithm.LengthToPoint ltop=new com.vividsolutions.jump.algorithm.LengthToPoint(line, point.getCoordinate());
					return NodeValue.makeDouble(ltop.getLength());
				}
				throw new ExprEvalException("Input geometries were not a point and a linestring");
			} catch (MismatchedDimensionException | TransformException | FactoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new ExprEvalException("An exception occurred: "+e.getMessage());
			}

	}

}
