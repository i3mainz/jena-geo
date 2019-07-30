package de.hsmainz.cs.semgis.arqextension.geometry.exporter;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.PrecisionModel;
import org.locationtech.jts.precision.GeometryPrecisionReducer;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class AsTextRaw extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue arg0) {
        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            Geometry geom = geometry.getXYGeometry();
            Geometry geom_mod=GeometryPrecisionReducer.reduce(geom, new PrecisionModel(PrecisionModel.FLOATING));
            return NodeValue.makeString(geom_mod.toText());
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

}
