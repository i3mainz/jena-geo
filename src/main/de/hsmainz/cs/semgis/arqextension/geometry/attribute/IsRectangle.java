package de.hsmainz.cs.semgis.arqextension.geometry.attribute;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class IsRectangle extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v1) {	
		try {
            GeometryWrapper geom1 = GeometryWrapper.extract(v1);
            return NodeValue.makeBoolean(geom1.getParsingGeometry().isRectangle());
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

}
