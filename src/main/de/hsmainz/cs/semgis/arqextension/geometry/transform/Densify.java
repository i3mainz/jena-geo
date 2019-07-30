package de.hsmainz.cs.semgis.arqextension.geometry.transform;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Geometry;

import org.locationtech.jts.densify.Densifier;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

public class Densify extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		try {
	        GeometryWrapper geometry = GeometryWrapper.extract(v1);
	        Geometry geom = geometry.getXYGeometry();
	        Double tolerance=v2.getDouble();
	        Geometry result=Densifier.densify(geom, tolerance);
	        return GeometryWrapperFactory.createGeometry(result, geometry.getSrsURI(), geometry.getGeometryDatatypeURI()).asNodeValue();
	    } catch (DatatypeFormatException ex) {
	        throw new ExprEvalException(ex.getMessage(), ex);
	    }
	}

}
