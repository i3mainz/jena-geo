package de.hsmainz.cs.semgis.arqextension.geometry;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase4;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

/**
 * Filters away vertex points based on their m-value. Returns a geometry with only vertex points that have a m-value larger or equal to the min value and smaller or equal to the max value. If max-value argument is left out only min value is considered. If fourth argument is left out the m-value will not be in the resulting geoemtry. If resulting geometry have too few vertex points left for its geometry type an empty geoemtry will be returned. In a geometry collection geometries without enough points will just be left out silently.
 *
 */
public class FilterByM extends FunctionBase4 {

    @Override
    public NodeValue exec(NodeValue arg0,NodeValue arg1, NodeValue arg2, NodeValue arg3) {

        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            Geometry geom = geometry.getParsingGeometry();
            Double precisionMin=arg1.getDouble();
            Double precisionMax=arg2.getDouble();
            Boolean returnM=arg3.getBoolean();
            List<Coordinate> result=new ArrayList<Coordinate>();
            for(Coordinate coord:geom.getCoordinates()) {
            	if(!Double.isNaN(coord.getM()) && coord.getM()>precisionMin && coord.getM()<precisionMax) {
            		if(returnM)
            			result.add(coord);
            	}else {
            		if(!returnM)
            			result.add(coord);
            	}
            }
            return LiteralUtils.createGeometry(result, geom.getGeometryType(), geometry).asNodeValue();
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
    }

}
