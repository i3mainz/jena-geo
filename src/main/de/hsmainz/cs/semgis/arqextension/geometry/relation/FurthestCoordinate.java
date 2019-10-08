package de.hsmainz.cs.semgis.arqextension.geometry.relation;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

public class FurthestCoordinate extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		GeometryWrapper geom1 = GeometryWrapper.extract(v1);
        GeometryWrapper geom2 = GeometryWrapper.extract(v2);
		GeometryFactory fac=new GeometryFactory();
        try {
        	Point point;
        	if(geom1.getXYGeometry() instanceof Point) {
        		point=(Point)geom1.getXYGeometry();
        	}else {

        		point=fac.createPoint(geom1.getXYGeometry().getCoordinates()[0]);
        	}
			GeometryWrapper transGeom2 = geom2.transform(geom1.getSRID());
			Double maxDistance=0.;
			Coordinate maxDistanceCoord=null;
	        for(Coordinate coord:transGeom2.getXYGeometry().getCoordinates()) {
	        	Double dist=point.getCoordinate().distance(coord);
	        	if(dist>maxDistance) {
	        		maxDistance=dist;
	        		maxDistanceCoord=coord;
	        	}
	        }
            GeometryWrapper lineStringWrapper = GeometryWrapperFactory.createPoint(maxDistanceCoord, geom1.getSrsURI(), geom1.getGeometryDatatypeURI());
            return lineStringWrapper.asNodeValue();
        } catch (MismatchedDimensionException | TransformException | FactoryException e) {
			throw new RuntimeException(e.getMessage());
		}

	}

}
