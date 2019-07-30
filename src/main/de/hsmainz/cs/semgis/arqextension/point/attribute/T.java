package de.hsmainz.cs.semgis.arqextension.point.attribute;

import java.util.Calendar;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class T extends FunctionBase1 {

    @Override
    public NodeValue exec(NodeValue arg0) {

        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            Geometry geom = geometry.getXYGeometry();

            if (geom instanceof Point) {
            	Calendar cal = Calendar.getInstance();
            	cal.setTime(((Point) geom).getCoordinate().getT());
                return NodeValue.makeDate(cal);
               
            }

            throw new ExprEvalException("Given geometry is not a point");
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
    }

}
