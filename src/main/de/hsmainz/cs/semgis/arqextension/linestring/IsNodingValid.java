package de.hsmainz.cs.semgis.arqextension.linestring;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.noding.FastNodingValidator;
import org.locationtech.jts.noding.SegmentStringUtil;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class IsNodingValid extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {	
		 try {
	            GeometryWrapper geometry = GeometryWrapper.extract(v);
	            Geometry geom = geometry.getXYGeometry();
	   		 	FastNodingValidator nv = new FastNodingValidator(
				        SegmentStringUtil.extractNodedSegmentStrings(geom));
				return NodeValue.makeBoolean(nv.isValid());
	        } catch (DatatypeFormatException ex) {
	            throw new ExprEvalException(ex.getMessage(), ex);
	        }

	}

}
