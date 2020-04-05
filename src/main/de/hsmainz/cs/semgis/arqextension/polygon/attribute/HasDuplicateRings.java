package de.hsmainz.cs.semgis.arqextension.polygon.attribute;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Polygon;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class HasDuplicateRings extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
        try {
            GeometryWrapper geometry = GeometryWrapper.extract(v);
            Geometry geom = geometry.getParsingGeometry();
            if (geom instanceof Polygon) {    	
            	Polygon poly=(Polygon) geom;
            	for(int i=0;i<poly.getNumInteriorRing();i++) {
            		for(int j=0;j<poly.getNumInteriorRing();j++) {
            			if(poly.getInteriorRingN(i).equals(poly.getInteriorRingN(j))) {
            				return NodeValue.TRUE;
            			}
            		}
            	}
            	return NodeValue.FALSE;
            }
            return NodeValue.FALSE;
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

	
}
