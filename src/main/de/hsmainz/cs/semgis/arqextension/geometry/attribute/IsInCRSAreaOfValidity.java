package de.hsmainz.cs.semgis.arqextension.geometry.attribute;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.opengis.referencing.cs.CoordinateSystemAxis;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class IsInCRSAreaOfValidity extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
		 GeometryWrapper geometry = GeometryWrapper.extract(v);
         Geometry geom = geometry.getXYGeometry();
		CoordinateSystemAxis x = crs.getCoordinateSystem().getAxis(0);
	    CoordinateSystemAxis y = crs.getCoordinateSystem().getAxis(1);
	    boolean xUnbounded = Double.isInfinite(x.getMinimumValue()) && Double.isInfinite(x.getMaximumValue());
        boolean yUnbounded = Double.isInfinite(y.getMinimumValue()) && Double.isInfinite(y.getMaximumValue());
        if (xUnbounded && yUnbounded) {
            return false;
        }
        Coordinate[] c = geom.getCoordinates();
        for (int i = 0; i < c.length; i++) {
            if (!xUnbounded && ((c[i].x < x.getMinimumValue()) || (c[i].x > x.getMaximumValue()))) {
            	return false;
            }
            if (!yUnbounded && ((c[i].y < y.getMinimumValue()) || (c[i].y > y.getMaximumValue()))) {
                return false;
            }
        }
		return true;
	}

}
