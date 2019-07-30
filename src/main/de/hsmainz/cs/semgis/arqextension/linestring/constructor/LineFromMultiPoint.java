package de.hsmainz.cs.semgis.arqextension.linestring.constructor;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.MultiPoint;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;
/**
 * Creates a LineString from a MultiPoint geometry.
 *
 */
public class LineFromMultiPoint extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
		try {
			GeometryWrapper geom1 = GeometryWrapper.extract(v);
			if(geom1.getGeometryType()=="MultiPoint"){
				MultiPoint mp=(MultiPoint) geom1.getXYGeometry();
            	GeometryWrapper pointWrapper = GeometryWrapperFactory.createLineString(mp.getCoordinates(), "<http://www.opengis.net/def/crs/EPSG/0/"+mp.getSRID()+">", WKTDatatype.URI);	
                return pointWrapper.asNodeValue();
			}			
			throw new ExprEvalException("First argument is not a MultiPoint", null);
		} catch (DatatypeFormatException ex) {
			throw new ExprEvalException(ex.getMessage(), ex);
		}
	}

}
