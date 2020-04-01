package de.hsmainz.cs.semgis.arqextension.point.attribute;

import java.util.Calendar;
import java.util.Date;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class TMin extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
        try {
            GeometryWrapper geometry = GeometryWrapper.extract(v);
            Geometry geo=geometry.getXYGeometry();
            Date minT=new Date(System.currentTimeMillis());
            for(Coordinate coord:geo.getCoordinates()) {
            	if(coord.getT()!=null && coord.getT().before(minT)) {
            		minT=coord.getT();
            	}
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(minT);
            return NodeValue.makeDate(cal);
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

}
