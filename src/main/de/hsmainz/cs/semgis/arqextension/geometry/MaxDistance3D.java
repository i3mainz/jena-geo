package de.hsmainz.cs.semgis.arqextension.geometry;

import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.operation.distance3d.Distance3DOp;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class MaxDistance3D extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1,NodeValue v2) {
		GeometryWrapper geom1 = GeometryWrapper.extract(v1);
        GeometryWrapper geom2 = GeometryWrapper.extract(v2);
        Double maxDistance=0.;
        GeometryFactory fac=new GeometryFactory();
        try {
			GeometryWrapper transGeom2 = geom2.transform(geom1.getSRID());
			for(Coordinate coord:geom1.getXYGeometry().getCoordinates()) {
				for(Coordinate coord2:transGeom2.getXYGeometry().getCoordinates()) {
					Double curdistance=Distance3DOp.distance(fac.createPoint(coord), fac.createPoint(coord2));
					if(curdistance>maxDistance) {
						maxDistance=curdistance;
					}
				}
			}
			return NodeValue.makeDouble(maxDistance);
        } catch (TransformException | FactoryException e) {
        	throw new ExprEvalException("Error in distance calculation");
		}
	}

}
