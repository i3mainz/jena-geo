package de.hsmainz.cs.semgis.arqextension.geometry;

import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Coordinate;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

/**
 * Returns the 2-dimensional largest distance between two geometries in projected units.
 *
 */
public class MaxDistance extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
        GeometryWrapper geom1 = GeometryWrapper.extract(v1);
        GeometryWrapper geom2 = GeometryWrapper.extract(v2);
        Double maxDistance=0.;
        try {
			GeometryWrapper transGeom2 = geom2.transform(geom1.getSRID());
			for(Coordinate coord:geom1.getXYGeometry().getCoordinates()) {
				for(Coordinate coord2:transGeom2.getXYGeometry().getCoordinates()) {
					Double curdistance=coord.distance(coord2);
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
