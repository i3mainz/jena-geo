package de.hsmainz.cs.semgis.arqextension.point.attribute;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class MMin extends FunctionBase1 {

    @Override
    public NodeValue exec(NodeValue arg0) {

        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            Geometry geo=geometry.getParsingGeometry();
            Double minM=Double.MAX_VALUE;
            for(Coordinate coord:geo.getCoordinates()) {
            	if(!Double.isNaN(coord.getM()) && coord.getM()<minM) {
            		minM=coord.getM();
            	}
            }
            return NodeValue.makeDouble(minM);
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
    }
}