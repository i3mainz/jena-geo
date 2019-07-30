package de.hsmainz.cs.semgis.arqextension.geometry.editor;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class AddMeasure extends FunctionBase3 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2, NodeValue v3) {
		try {
            GeometryWrapper geometry = GeometryWrapper.extract(v1);

        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
		Double measure_start=v2.getDouble();
		Double measure_end=v3.getDouble();
        throw new UnsupportedOperationException("Not supported yet.");
	}

}
