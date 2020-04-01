package de.hsmainz.cs.semgis.arqextension.geometry.constructor;

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
 * Creates a geometry instance from a Well-Known Binary geometry representation (WKB) and optional SRID.
 *
 */
public class GeomFromWKB extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue arg0) {
        try {
            String wkbstring=arg0.getString();
            WKBReader reader=new WKBReader();
            Geometry geom=reader.read(wkbstring.getBytes());
            GeometryWrapper pointWrapper = GeometryWrapperFactory.createPoint(geom.getCoordinate(), "<http://www.opengis.net/def/crs/EPSG/0/"+geom.getSRID()+">", WKTDatatype.URI);	
            return pointWrapper.asNodeValue();           
        } catch (DatatypeFormatException | ParseException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

}
