package de.hsmainz.cs.semgis.arqextension.geometry.attribute;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

/**
 * Returns the diagonal of the supplied geometry's bounding box.
 *
 */
public class BoundingDiagonal extends FunctionBase1{

	@Override
	public NodeValue exec(NodeValue arg0) {
        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            Geometry geom = geometry.getParsingGeometry();
            org.locationtech.jts.geom.Envelope env=geom.getEnvelopeInternal();
            Coordinate lowerCorner=new Coordinate(env.getMinX(),env.getMinY());
            Coordinate upperCorner=new Coordinate(env.getMaxX(),env.getMaxY());
            GeometryWrapper lineStringWrapper = GeometryWrapperFactory.createLineString(new Coordinate[] {lowerCorner,upperCorner}, WKTDatatype.URI);
            return lineStringWrapper.asNodeValue();          
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

}
