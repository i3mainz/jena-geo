package de.hsmainz.cs.semgis.arqextension.geometry.attribute;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.apache.sis.referencing.CRS;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class HasHorizontalCRS extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
		try {
            GeometryWrapper geometry = GeometryWrapper.extract(v);
            return NodeValue.makeBoolean(CRS.isHorizontalCRS(geometry.getCRS()));
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

}
