package de.hsmainz.cs.semgis.arqextension.geometry.attribute;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Geometry;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class MinimumDiameter extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
		try {
	        GeometryWrapper geometry = GeometryWrapper.extract(v);
	        Geometry geom = geometry.getParsingGeometry();
	        org.locationtech.jts.algorithm.MinimumDiameter mindiam=new org.locationtech.jts.algorithm.MinimumDiameter(geom);
	        return NodeValue.makeDouble(mindiam.getDiameter().getLength());
	    } catch (DatatypeFormatException ex) {
	        throw new ExprEvalException(ex.getMessage(), ex);
	    }
	}


}
