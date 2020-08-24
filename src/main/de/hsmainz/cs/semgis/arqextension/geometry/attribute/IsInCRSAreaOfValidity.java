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
         Geometry geom = geometry.getParsingGeometry();
		CoordinateSystemAxis x = geometry.getCRS().getCoordinateSystem().getAxis(0);
	    CoordinateSystemAxis y = geometry.getCRS().getCoordinateSystem().getAxis(1);
	    boolean xUnbounded = Double.isInfinite(x.getMinimumValue()) && Double.isInfinite(x.getMaximumValue());
        boolean yUnbounded = Double.isInfinite(y.getMinimumValue()) && Double.isInfinite(y.getMaximumValue());
        if (xUnbounded && yUnbounded) {
            return NodeValue.makeBoolean(false);
        }
        Coordinate[] c = geom.getCoordinates();
        for (int i = 0; i < c.length; i++) {
            if (!xUnbounded && ((c[i].x < x.getMinimumValue()) || (c[i].x > x.getMaximumValue()))) {
            	return NodeValue.makeBoolean(false);
            }
            if (!yUnbounded && ((c[i].y < y.getMinimumValue()) || (c[i].y > y.getMaximumValue()))) {
                return NodeValue.makeBoolean(false);
            }
        }
		return NodeValue.makeBoolean(true);
	}

}
