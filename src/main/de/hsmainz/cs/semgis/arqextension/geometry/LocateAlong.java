package de.hsmainz.cs.semgis.arqextension.geometry;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LineSegment;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.MultiPoint;
import org.locationtech.jts.geom.Polygon;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

public class LocateAlong extends FunctionBase3{

	@Override
	public NodeValue exec(NodeValue arg0, NodeValue arg1, NodeValue arg2) {
		GeometryWrapper geom1 = GeometryWrapper.extract(arg0);
        Double a_measure=arg1.getDouble();
        Double offset=arg2.getDouble();
        MultiPoint mp=locateAlong(geom1.getXYGeometry(), a_measure, offset);
		return GeometryWrapperFactory.createMultiPoint(Arrays.asList(mp.getCoordinates()), geom1.getSrsURI(),geom1.getGeometryDatatypeURI()).asNodeValue();
	}

	 public static MultiPoint locateAlong(Geometry geom,
             double segmentLengthFraction,
             double offsetDistance) {
if (geom == null) {
return null;
}
if (geom.getDimension() == 0) {
return null;
}
Set<Coordinate> result = new HashSet<Coordinate>();
for (int i = 0; i < geom.getNumGeometries(); i++) {
Geometry subGeom = geom.getGeometryN(i);
if (subGeom instanceof Polygon) {
// Ignore hole
result.addAll(computePoints(((Polygon) subGeom).getExteriorRing().getCoordinates(),
segmentLengthFraction, offsetDistance));
} else if (subGeom instanceof LineString) {
result.addAll(computePoints(subGeom.getCoordinates(),
segmentLengthFraction, offsetDistance));
}
}
return geom.getFactory().createMultiPoint(
result.toArray(new Coordinate[0]));
}

private static Set<Coordinate> computePoints(Coordinate[] coords,
                     double segmentLengthFraction,
                     double offsetDistance) {
Set<Coordinate> pointsToAdd = new HashSet<Coordinate>();
for (int j = 0; j < coords.length - 1; j++) {
LineSegment segment = new LineSegment(coords[j], coords[j + 1]);
pointsToAdd.add(segment.pointAlongOffset(segmentLengthFraction, offsetDistance));
}
return pointsToAdd;
	
}
}
