package de.hsmainz.cs.semgis.arqextension.geometry;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class Points extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue arg0) {
        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            Geometry geom = geometry.getXYGeometry();
            GeometryFactory fac=new GeometryFactory();
            List<Point> res=new LinkedList<Point>();
            for(Coordinate coord:geom.getCoordinates()) {
            	res.add(fac.createPoint(coord));
            }
            GeometryWrapper mPointWrapper = GeometryWrapperFactory.createMultiPoint (Arrays.asList(geom.getCoordinates()),WKTDatatype.URI);           
            return mPointWrapper.asNodeValue();
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

}
