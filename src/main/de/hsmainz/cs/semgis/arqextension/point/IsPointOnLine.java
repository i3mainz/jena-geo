package de.hsmainz.cs.semgis.arqextension.point;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.algorithm.CGAlgorithms;
import org.locationtech.jts.geom.Geometry;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class IsPointOnLine extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		try {
	        GeometryWrapper pointgeometry = GeometryWrapper.extract(v1);
	        Geometry pointgeom = pointgeometry.getXYGeometry();
	        GeometryWrapper ringgeometry = GeometryWrapper.extract(v1);
	        Geometry ringgeom = ringgeometry.getXYGeometry();
	        CGAlgorithms algos=new CGAlgorithms();
	        return NodeValue.makeBoolean(algos.isOnLine(pointgeom.getCoordinate(), ringgeom.getCoordinates()));
	    } catch (DatatypeFormatException ex) {
	        throw new ExprEvalException(ex.getMessage(), ex);
	    }
	}

}
