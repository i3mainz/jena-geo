package de.hsmainz.cs.semgis.arqextension.linestring.constructor;

import java.math.BigInteger;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.EncodedPolylineDatatype;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

/**
 * Creates a LineString from an Encoded Polyline.
 *
 */
public class LineFromEncodedPolyline extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v,NodeValue v2) {
		 String polyline=v.asString();
		 BigInteger precision=v2.getInteger();
    	GeometryWrapper pointWrapper = GeometryWrapperFactory.createLineString(EncodedPolylineDatatype.decodePolyline(polyline, precision.intValue()), "<http://www.opengis.net/def/crs/EPSG/0/4326>", WKTDatatype.URI);	
        return pointWrapper.asNodeValue();
	}

	
	/*public static void main(String[] args) {
		String polyline = "onynWggt_xCga@~EgxBzO}fA`BCoE|EIz]i@t`@m@vwByOfs@iJj{B_a@zcDig@jmCgSva@sDdhDs]pg@qMneAiXz`AwZ~VeLpn@eYdm@eUpKsAbKeAxx@oB`r@OfGx@`LxAlc@zFjZ~Ff{A~Y|WxFr_@rA~bAIneAKzFbAxZsLdlAmIdgAmKzUoFjwAyV~SkGtY{ItMkDxVgHhH_AtP}BhRXbXd@xFJlIZzQpGl_@vPpPfLjFhHJtHB`R?vXIdC"; //This is an OSRM generated polyline
        List<Coordinate> pointList = LineFromEncodedPolyline.decodePolyline(polyline, 6); //Use 5 for google maps polyline
        // pointList contains the list of all Geocodes in sequence in the route
        for (Coordinate G: pointList) {
            System.out.print("[" + G.x + "," + G.y +"],");
        }
        System.out.println();
	}*/

}
