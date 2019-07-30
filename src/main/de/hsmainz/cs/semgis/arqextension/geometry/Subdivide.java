package de.hsmainz.cs.semgis.arqextension.geometry;

import java.math.BigInteger;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.triangulate.quadedge.QuadEdgeSubdivision;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class Subdivide extends FunctionBase2{

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		GeometryWrapper geometry = GeometryWrapper.extract(v1);
        Geometry geom = geometry.getXYGeometry();
		BigInteger maxAmount=v2.getInteger();
		//QuadEdgeSubdivision quadsub=new QuadEdgeSubdivision(geom.getEnvelope(), tolerance)
		return null;
	}

}
