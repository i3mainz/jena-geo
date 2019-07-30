package de.hsmainz.cs.semgis.arqextension.geometry;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Polygon;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

/**
 * Return the geometry as a MULTI* geometry.
 *
 */
public class Multi extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue arg0) {
        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            if(geometry.getGeometryType().toUpperCase().contains("MULTI")) {
            	return geometry.asNodeValue();
            }
            switch(geometry.getGeometryType()) {
            case "Point": 
            	return GeometryWrapperFactory.createMultiPoint(Arrays.asList(geometry.getXYGeometry().getCoordinates()), geometry.getSrsURI(), WKTDatatype.URI).asNodeValue();
            case "Polygon":
            	List<Polygon> polylist=new LinkedList<Polygon>();
            	polylist.add((Polygon)geometry.getXYGeometry());
            	return GeometryWrapperFactory.createMultiPolygon(polylist, geometry.getSrsURI(), WKTDatatype.URI).asNodeValue();	
            case "LineString":
            	List<LineString> linelist=new LinkedList<LineString>();
            	linelist.add((LineString)geometry.getXYGeometry());
            	return GeometryWrapperFactory.createMultiLineString(linelist, geometry.getSrsURI(), WKTDatatype.URI).asNodeValue();
            default:
            	throw new ExprEvalException("Geometry type does not have a Multi representation or is not supported", null);
            }
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

}
