package de.hsmainz.cs.semgis.arqextension.geometry.constructor;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.util.GeometricShapeFactory;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

public class MakeEllipse extends FunctionBase3 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2, NodeValue v3) {
		try {
            GeometryWrapper geometry = GeometryWrapper.extract(v1);
            Double width=v2.getDouble();
            Double height=v3.getDouble();
            Point p=null;
            if(geometry.getGeometryType().equalsIgnoreCase("Point")) {
            	p=(Point)geometry.getParsingGeometry();
            }else {
            	p=geometry.getParsingGeometry().getCentroid();
            }
            GeometricShapeFactory fac=new GeometricShapeFactory();
            fac.setCentre(new Coordinate(p.getX(), p.getY()));
            fac.setWidth(width);
            fac.setHeight(height);
            Geometry el=fac.createEllipse();	
            return GeometryWrapperFactory.createGeometry(el, geometry.getSrsURI(), geometry.getGeometryDatatypeURI()).asNodeValue();
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

}
