package de.hsmainz.cs.semgis.arqextension.envelope;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.SRSInfo;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;
import io.github.galbiston.geosparql_jena.spatial.filter_functions.FunctionBase5;
import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.locationtech.jts.geom.Envelope;

/**
 * Creates a rectangular Polygon formed from the given minimums and maximums. Input values must be in SRS specified by the SRID.
 *
 */
public class MakeEnvelope extends FunctionBase5 {

    @Override
    public NodeValue exec(NodeValue arg0, NodeValue arg1, NodeValue arg2, NodeValue arg3, NodeValue arg4) {
        try {

            Envelope env = new Envelope();
            env.init(arg0.getDouble(), arg2.getDouble(), arg1.getDouble(), arg3.getDouble());
            String srsURI = SRSInfo.convertSRID(arg4.getInteger());
            GeometryWrapper geometryWrapper = GeometryWrapperFactory.createPolygon(env, srsURI, WKTDatatype.URI);

            //return geometryWrapper.asNodeValue();
            throw new UnsupportedOperationException("Not supported yet.");
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
    }

}
