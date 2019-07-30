package de.hsmainz.cs.semgis.arqextension.geometry.exporter;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Geometry;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; 
/**
 * Return the Degrees, Minutes, Seconds representation of the given point.
 *
 */
public class AsLatLonText extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		GeometryWrapper geometry = GeometryWrapper.extract(v1);
        Geometry geom = geometry.getXYGeometry();
		String format=v2.getString();
		if(geom.getGeometryType().equalsIgnoreCase("POINT")) {
			return NodeValue.makeString(convertDecimalToLatLonText(geom.getCoordinate().x,false)+" "+convertDecimalToLatLonText(geom.getCoordinate().getY(),true));	
		}else {
			return NodeValue.makeString(convertDecimalToLatLonText(geom.getCentroid().getCoordinate().x,false)+" "+convertDecimalToLatLonText(geom.getCentroid().getCoordinate().getY(),true));

		}
	}

	public String convertDecimalToLatLonText(Double D, Boolean lng){
	    String dir;
		if(D<0) {
			if(lng) {
				dir="W";
			}else {
				dir="S";
			}
		}else {
			if(lng) {
				dir="E";
			}else {
				dir="N";
			}
		}
		Double deg=D<0?-D:D;
		Double min=D%1*60;
		Double sec=(D*60%1*6000)/100;
		return deg+"°"+min+"'"+sec+"\""+dir;
	}
	
}
