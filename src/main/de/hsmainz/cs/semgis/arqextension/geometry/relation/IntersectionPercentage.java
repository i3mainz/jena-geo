package de.hsmainz.cs.semgis.arqextension.geometry.relation;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class IntersectionPercentage extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		GeometryWrapper geom1 = GeometryWrapper.extract(v1);
        GeometryWrapper geom2 = GeometryWrapper.extract(v2);
        
    	Double db;
		try {
			db = (geom1.intersection(geom2).getXYGeometry().getArea()/geom2.getXYGeometry().getArea());
	    	if(db.equals(Double.NaN)){
	    		return NodeValue.makeDouble(0.);
	    	}
	        return NodeValue.makeDouble(db);
		} catch (MismatchedDimensionException | FactoryException | TransformException e) {
			// TODO Auto-generated catch block
			return NodeValue.makeString(e.getMessage());
		}

	}

}
