package de.hsmainz.cs.semgis.arqextension.linestring.constructor;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKBReader;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

/**
 * Makes a LINESTRING from WKB with the given SRID
 *
 */
public class LineFromWKB extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue arg0) {
        try {
            String wkbstring=arg0.getString();
            WKBReader reader=new WKBReader();
            Geometry geom=reader.read(wkbstring.getBytes());
            if("LINESTRING".equals(geom.getGeometryType().toUpperCase())){
            	GeometryWrapper pointWrapper = GeometryWrapperFactory.createLineString(geom.getCoordinates(), "<http://www.opengis.net/def/crs/EPSG/0/"+geom.getSRID()+">", WKTDatatype.URI);	
                return pointWrapper.asNodeValue();
            }else {
            	throw new ExprEvalException("WKB does not represent a LineString", null);
            }
            
        } catch (DatatypeFormatException | ParseException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}
}
