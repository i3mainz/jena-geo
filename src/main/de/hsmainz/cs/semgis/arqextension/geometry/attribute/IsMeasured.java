package de.hsmainz.cs.semgis.arqextension.geometry.attribute;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class IsMeasured extends FunctionBase1{

	@Override
	public NodeValue exec(NodeValue v) {
	       try {
	            GeometryWrapper geometry = GeometryWrapper.extract(v);
	            Geometry geom = geometry.getParsingGeometry();
	            Boolean isMeasured=true;
	            for(Coordinate coord:geom.getCoordinates()) {
	            	if(Double.isNaN(coord.getM())) {
	            		isMeasured=false;
	            	}
	            }
	            return NodeValue.makeNodeBoolean(isMeasured);
	        } catch (DatatypeFormatException ex) {
	            throw new ExprEvalException(ex.getMessage(), ex);
	        }
	}

}
