package de.hsmainz.cs.semgis.arqextension.geometry.attribute;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class IsSolid extends FunctionBase1{

	@Override
	public NodeValue exec(NodeValue v) {
	       try {
	            GeometryWrapper geometry = GeometryWrapper.extract(v);
	            Geometry geom = geometry.getParsingGeometry();
	            Boolean is3D=true;
	            for(Coordinate coord:geom.getCoordinates()) {
	            	if(Double.isNaN(coord.getZ())) {
	            		is3D=false;
	            	}
	            }
	            return NodeValue.makeNodeBoolean(is3D);
	        } catch (DatatypeFormatException ex) {
	            throw new ExprEvalException(ex.getMessage(), ex);
	        }
	}

}
