package de.hsmainz.cs.semgis.arqextension.geometry.exporter;

import java.io.ByteArrayOutputStream;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.datatype.geometry.GeobufDatatype;

/**
 * Return a Geobuf representation of a set of rows.
 *
 */
public class AsGeoBuf extends FunctionBase1{

	@Override
	public NodeValue exec(NodeValue v) {
        try {
            GeometryWrapper geometry = GeometryWrapper.extract(v);
            ByteArrayOutputStream output=new ByteArrayOutputStream();
            return NodeValue.makeString(GeobufDatatype.INSTANCE.unparse(geometry));
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

}
