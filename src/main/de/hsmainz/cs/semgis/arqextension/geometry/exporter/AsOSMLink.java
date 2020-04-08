package de.hsmainz.cs.semgis.arqextension.geometry.exporter;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.Geometry;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class AsOSMLink extends FunctionBase1{

	@Override
	public NodeValue exec(NodeValue v) {
		try {
            GeometryWrapper geometry = GeometryWrapper.extract(v);
            Geometry geom = geometry.getXYGeometry();    
            Envelope env = geom.getEnvelopeInternal();
            StringBuilder builder = new StringBuilder("http://www.openstreetmap.org/?");
            builder.append("minlon=").append(env.getMinY());
            builder.append("&minlat=").append(env.getMinX());
            builder.append("&maxlon=").append(env.getMaxY());
            builder.append("&maxlat=").append(env.getMaxX());
            Coordinate centre = env.centre();
            builder.append("&mlat=").append(centre.x);
            builder.append("&mlon=").append(centre.y);          
            return NodeValue.makeString(builder.toString());
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
		
	}

}
