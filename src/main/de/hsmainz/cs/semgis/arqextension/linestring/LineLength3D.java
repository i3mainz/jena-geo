package de.hsmainz.cs.semgis.arqextension.linestring;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateSequence;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Polygon;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

public class LineLength3D extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue arg0) {
        GeometryWrapper geometry = GeometryWrapper.extract(arg0);
        Geometry geom = geometry.getXYGeometry();
        Double length=length3D(geom);
        return NodeValue.makeDouble(length);
	}
	
	/**
	 * FROM org.h2gis.drivers.utility; GPL3
	 * 
	 */
	
	/**
     * Computes the length of a linestring specified by a sequence of points.
     * if a coordinate has a NaN z return 0.
     *
     * @param pts
     * the points specifying the linestring
     * @return the length of the linestring
     */
    public static double length3D(CoordinateSequence pts) {
            // optimized for processing CoordinateSequences
            int n = pts.size();
            if (n <= 1) {
                    return 0.0;
            }

            double len = 0.0;

            Coordinate p = new Coordinate();
            pts.getCoordinate(0, p);
            double x0 = p.x;
            double y0 = p.y;
            double z0 = p.z;

            if (Double.isNaN(z0)) {
                    return 0.0;
            }

            for (int i = 1; i < n; i++) {
                    pts.getCoordinate(i, p);

                    double x1 = p.x;
                    double y1 = p.y;
                    double z1 = p.z;
                    if (Double.isNaN(z1)) {
                            return 0.0;
                    }
                    double dx = x1 - x0;
                    double dy = y1 - y0;
                    double dz = z1 - z0;

                    len += Math.sqrt(dx * dx + dy * dy + dz * dz);
                    x0 = x1;
                    y0 = y1;
                    z0 = z1;
            }
            return len;
    }

    /**
     * Returns the 3D length of the geometry
     *
     *
     * @param geom
     * @return
     */
    public static double length3D(Geometry geom) {
            double sum = 0;
            for (int i = 0; i < geom.getNumGeometries(); i++) {
                    Geometry subGeom = geom.getGeometryN(i);
                    if (subGeom instanceof Polygon) {
                            sum += length3D((Polygon) subGeom);
                    } else if (subGeom instanceof LineString) {
                            sum += length3D((LineString) subGeom);
                    }
            }
            return sum;
    }

    /**
     * Returns the 3D perimeter of a line string.
     *
     * @param lineString
     * @return
     */
    public static double length3D(LineString lineString) {
            return length3D(lineString.getCoordinateSequence());
    }

    /**
     * Returns the 3D perimeter of a polygon
     *
     * @param polygon
     * @return
     */
    public static double length3D(Polygon polygon) {
            double len = 0.0;
            len += length3D(polygon.getExteriorRing().getCoordinateSequence());
            for (int i = 0; i < polygon.getNumInteriorRing(); i++) {
                    len += length3D(polygon.getInteriorRingN(i));
            }
            return len;
    }

}
