package de.hsmainz.cs.semgis.arqextension.geometry;

import javax.media.j3d.Bounds;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class Intersection3D extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		GeometryWrapper geom1 = GeometryWrapper.extract(v1);
        GeometryWrapper geom2 = GeometryWrapper.extract(v2);
        Vector3D vec=new Vec
		Bounds quadArrayBounds = quadArrayShape.getBounds();
		if (quadArrayBounds.intersect(new Point3d(startLine.x, startLine.y, startLine.z), 
		new Vector3d(endLine.x, endLine.y, endLine.z))){
		               // Action to do if Intesect is true
		            }

		return null;
	}

}
