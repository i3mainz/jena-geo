package de.hsmainz.cs.semgis.arqextension.linestring.exporter;

import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LineString;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.datatype.EncodedPolylineDatatype;

/**
 * Returns an Encoded Polyline from a LineString geometry.
 *
 */
public class AsEncodedPolyline extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue arg0) {
		GeometryWrapper geometry = GeometryWrapper.extract(arg0);
        Geometry geom = geometry.getXYGeometry();

        if (geom instanceof LineString) {
        	String result=EncodedPolylineDatatype.encodePolyline((LineString)geom);
        	return NodeValue.makeString(result);
        }else {
        	throw new ExprEvalException("Given input does not represent a LineString", null);
        }
	}
	


	
}
