package de.hsmainz.cs.semgis.arqextension.point.attribute;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

/**
 * Returns Z maxima of a bounding box 2d or 3d or a geometry.
 *
 */
public class ZMax extends FunctionBase1 {

    @Override
    public NodeValue exec(NodeValue arg0) {

        GeometryWrapper geometry = GeometryWrapper.extract(arg0);
        Geometry geo=geometry.getParsingGeometry();
        Double maxZ=Double.MIN_VALUE;
        for(Coordinate coord:geo.getCoordinates()) {
        	if(!Double.isNaN(coord.getZ()) && coord.getZ()>maxZ) {
        		maxZ=coord.getZ();
        	}
        }
        return NodeValue.makeDouble(maxZ);
    }
}
