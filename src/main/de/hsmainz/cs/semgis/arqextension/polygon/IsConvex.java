package de.hsmainz.cs.semgis.arqextension.polygon;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; 
public class IsConvex extends FunctionBase1 {

	//https://gis.stackexchange.com/questions/157567/testing-if-geometry-is-convex-using-jts
	public static boolean isConvex(Polygon p) {
        LinearRing r = (LinearRing) p.getExteriorRing();
        int sign = 0;
        for(int i = 1; i < r.getNumPoints(); ++i) {
            Coordinate c0 = r.getCoordinateN(i == 0 ? r.getNumPoints() - 1 : i - 1);
            Coordinate c1 = r.getCoordinateN(i);
            Coordinate c2 = r.getCoordinateN(i == r.getNumPoints() - 1 ? 0 : i + 1);
            double dx1 = c1.x - c0.x;
            double dy1 = c1.y - c0.y;
            double dx2 = c2.x - c1.x;
            double dy2 = c2.y - c2.y;
            double z = dx1 * dy2 - dy1 * dx2;
            int s = z >= 0.0 ? 1 : -1;
            if(sign == 0) {
                sign = s; 
            } else if(sign != s) {
                return false;
            }
        }
        return true;
    }

	@Override
	public NodeValue exec(NodeValue arg0) {
		try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            Geometry geom = geometry.getXYGeometry();
            if (geom instanceof LinearRing || geom instanceof Polygon) {
                boolean isConvex = isConvex((Polygon)geom);
                return NodeValue.makeNodeBoolean(isConvex);
            }
            return NodeValue.FALSE;
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}
}
