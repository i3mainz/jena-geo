package de.hsmainz.cs.semgis.arqextension.geometry;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class PartOfGeometryBefore extends FunctionBase2 {

    @Override
	    public NodeValue exec(NodeValue arg0,NodeValue arg1) {
    		Date date=arg1.getDateTime().toGregorianCalendar().getTime();
	        try {
	            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
	            Geometry geom = geometry.getXYGeometry();
	            List<Coordinate> newcoords=new LinkedList<Coordinate>();
	            for(Coordinate coord:geom.getCoordinates()) {
	            	if(coord.getT()!=null && coord.getT().before(date)) {
	            		newcoords.add(coord);
	            	}
	            }
	            GeometryWrapper res=LiteralUtils.createGeometry(newcoords, geom.getGeometryType(), geometry);
	            return res.asNodeValue();
	        } catch (DatatypeFormatException ex) {
	            throw new ExprEvalException(ex.getMessage(), ex);
	        }
	    }
}
