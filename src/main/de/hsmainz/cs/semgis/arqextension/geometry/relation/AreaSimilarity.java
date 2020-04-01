package de.hsmainz.cs.semgis.arqextension.geometry.relation;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.algorithm.match.AreaSimilarityMeasure;
import org.locationtech.jts.geom.Geometry;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class AreaSimilarity extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		try {
	        GeometryWrapper geometry = GeometryWrapper.extract(v1);
	        Geometry geom = geometry.getXYGeometry();
	        GeometryWrapper geometry2 = GeometryWrapper.extract(v2);
	        Geometry geom2 = geometry2.getXYGeometry();
	        AreaSimilarityMeasure areasim=new AreaSimilarityMeasure();
	        return NodeValue.makeDouble(areasim.measure(geom, geom2));
	    } catch (DatatypeFormatException ex) {
	        throw new ExprEvalException(ex.getMessage(), ex);
	    
	}
	}

}
