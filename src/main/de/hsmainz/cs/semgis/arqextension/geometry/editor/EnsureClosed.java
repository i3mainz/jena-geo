package de.hsmainz.cs.semgis.arqextension.geometry.editor;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class EnsureClosed extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
		GeometryWrapper geometry = GeometryWrapper.extract(v);
		Geometry geom=geometry.getXYGeometry();
		Coordinate[] array=geom.getCoordinates();
		if(!array[0].equals(array[array.length-1])) {
			List<Coordinate> coords=new LinkedList<Coordinate>();
			for(Coordinate coord:Arrays.asList(array)) {
				coords.add(coord);
			}
			coords.add(array[0]);
			GeometryWrapper res=LiteralUtils.createGeometry(coords, geometry.getGeometryType(), geometry);
            return res.asNodeValue();
		}
		return geometry.asNodeValue();
	}

}
