package de.hsmainz.cs.semgis.arqextension.geometry;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import org.joda.time.Days;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class FilterByT extends FunctionBase3 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2, NodeValue v3) {

        try {
            GeometryWrapper geometry = GeometryWrapper.extract(v1);
            Geometry geom = geometry.getParsingGeometry();
            Date date=v2.getDateTime().toGregorianCalendar().getTime();
            Double precisionMax=v3.getDouble();
            Days tolerance=Days.days(5);
            List<Coordinate> result=new ArrayList<Coordinate>();
            for(Coordinate coord:geom.getCoordinates()) {
            	if(coord.getT()!=null && coord.getT()>precisionMin && coord.getM()<precisionMax) {
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
