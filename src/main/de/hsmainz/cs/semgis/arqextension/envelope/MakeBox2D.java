package de.hsmainz.cs.semgis.arqextension.envelope;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Envelope;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class MakeBox2D extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue arg0, NodeValue arg1) {
        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            GeometryWrapper geometry2 = GeometryWrapper.extract(arg1);
            Envelope box2d=new Envelope(geometry.getXYGeometry().getCoordinate(),geometry2.getXYGeometry().getCoordinate());
            GeometryWrapper geometryWrapper = GeometryWrapperFactory.createPolygon(box2d, geometry.getSrsURI(), WKTDatatype.URI);

            return geometryWrapper.asNodeValue();
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

}
