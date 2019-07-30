package de.hsmainz.cs.semgis.arqextension.geometry.relation;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Geometry;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class IsMorePrecise extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		try {
            GeometryWrapper geometry = GeometryWrapper.extract(v1);
            Geometry geom = geometry.getXYGeometry();
            GeometryWrapper geometry2 = GeometryWrapper.extract(v1);
            Geometry geom2 = geometry2.getXYGeometry();
            if(geom.getPrecisionModel().getMaximumSignificantDigits()>geom2.getPrecisionModel().getMaximumSignificantDigits()) {
            	return NodeValue.makeInteger(1);
            }else if(geom.getPrecisionModel().getMaximumSignificantDigits()==geom2.getPrecisionModel().getMaximumSignificantDigits()) {
            	return NodeValue.makeInteger(0);
            }else {
            	return NodeValue.makeInteger(-1);
            }
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

}
