package de.hsmainz.cs.semgis.arqextension.polygon.editor;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

public class SetRing extends FunctionBase3 {

	@Override
	public NodeValue exec(NodeValue v,NodeValue v2,NodeValue v3) {
        try {
            GeometryWrapper geometry = GeometryWrapper.extract(v);
            Geometry geom = geometry.getXYGeometry();
            BigInteger index=v2.getInteger();
            if(geom instanceof Polygon) {   
            	Polygon poly=(Polygon) geom;
            	List<LinearRing> rings=new LinkedList<LinearRing>();
            	for(int i=0;i<poly.getNumInteriorRing();i++) {
            		if(i!=index.intValue()) {
                		rings.add((LinearRing)poly.getInteriorRingN(i));
            		}
            	}
            	return GeometryWrapperFactory.createPolygon((LinearRing)poly.getExteriorRing(), 
        			rings.toArray(new LinearRing[0]), geometry.getGeometryDatatypeURI()).asNodeValue();
            }
            throw new ExprEvalException("Given geometry is not a Polygon");
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

}
