package de.hsmainz.cs.semgis.arqextension.geometry.constructor;

import java.text.ParseException;

import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.apache.sis.index.GeoHashCoder;
import org.locationtech.jts.geom.Coordinate;
import org.opengis.geometry.DirectPosition;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;
/**
 * Return a geometry from a GeoHash string.
 *
 */
public class GeomFromGeoHash extends FunctionBase2 {

	GeoHashCoder coder=new GeoHashCoder();
	
	@Override
	public NodeValue exec(NodeValue v, NodeValue v2) {
		String geojson=v.getString();
		Double precision=v2.getDouble();
		try {
			DirectPosition pos=coder.decode(geojson);
			Coordinate coord=new Coordinate(pos.getCoordinate()[1], pos.getCoordinate()[0]);
			return GeometryWrapperFactory.createPoint(coord,WKTDatatype.URI).asNodeValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ExprEvalException("An exception occured: "+e.getMessage());
		}
	}

}
