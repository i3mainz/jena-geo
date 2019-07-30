package de.hsmainz.cs.semgis.arqextension.linestring;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.MultiLineString;

import org.locationtech.jump.algorithm.LengthSubstring;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

public class LineSubstring extends FunctionBase3{

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2, NodeValue v3) {
        try {
            GeometryWrapper geometry = GeometryWrapper.extract(v1);
            Geometry geom = geometry.getXYGeometry();
            Double startPercentage=v2.getDouble();
            Double endPercentage=v3.getDouble();
            LengthSubstring lsub=new LengthSubstring((LineString)geom);
            if (geom instanceof MultiLineString) {
            	List<Coordinate> resultcoords=new LinkedList<Coordinate>();
            	for(int i=0;i<((MultiLineString) geom).getLength();i++) {
            		resultcoords.addAll(Arrays.asList(((MultiLineString) geom).getGeometryN(i).getCoordinates()));
            	}
            	return GeometryWrapperFactory.createLineString(resultcoords.toArray(new Coordinate[0]), geometry.getGeometryDatatypeURI()).asNodeValue();
            }
            return NodeValue.nvNothing;
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

}
