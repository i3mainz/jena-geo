package de.hsmainz.cs.semgis.arqextension.point;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

/**
 * Returns the geometric median of a MultiPoint. 
 *
 */
public class GeometricMedian extends FunctionBase1{

	double lower_limit = 0.01;
	
	@Override
	public NodeValue exec(NodeValue arg0) {
        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            Geometry geom = geometry.getXYGeometry();
            Coordinate median=getGeoMedian(geom.getCentroid().getCoordinate(), geom.getCoordinates());
            return GeometryWrapperFactory.createPoint(median, geometry.getGeometryDatatypeURI()).asNodeValue();
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}
	
	
    public Coordinate getGeoMedian(Coordinate start,Coordinate[] points) {
        double cx = 0;
        double cy = 0;

        double centroidx = start.x;
        double centroidy = start.y;
        do {
            double totalWeight = 0;
            for (int i = 0; i < points.length; i++) {
                Coordinate pt = points[i];
                double weight = 1 / distance(pt.x, pt.y, centroidx, centroidy);
                cx += pt.x * weight;
                cy += pt.y * weight;
                totalWeight += weight;
            }
            cx /= totalWeight;
            cy /= totalWeight;
        } while (Math.abs(cx - centroidx) > 0.5
                || Math.abs(cy - centroidy) > 0.5);// Probably this condition
                                                    // needs to change

        return new Coordinate(cx, cy);
    }
    
    private static double distance(double x1, double y1, double x2, double y2) {
        x1 -= x2;
        y1 -= y2;
        return Math.sqrt(x1 * x1 + y1 * y1);
    }

}
