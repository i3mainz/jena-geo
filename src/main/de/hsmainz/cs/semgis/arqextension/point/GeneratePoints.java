package de.hsmainz.cs.semgis.arqextension.point;

import java.math.BigInteger;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.shape.random.RandomPointsBuilder;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

public class GeneratePoints extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		GeometryWrapper geometry = GeometryWrapper.extract(v1);
        Geometry geom=geometry.getXYGeometry();
        BigInteger amount=v2.getInteger();
        try {
		RandomPointsBuilder shapeBuilder = new RandomPointsBuilder(geom.getFactory());
		shapeBuilder.setNumPoints(amount.intValue());
		if(!geom.getGeometryType().equalsIgnoreCase("Polygon")) {
			System.out.println(geom.getGeometryType());
			Geometry convhull=geom.convexHull();
			System.out.println(convhull.getGeometryType());
			if(!convhull.getGeometryType().equalsIgnoreCase("Polygon")) {
				convhull=convhull.convexHull();
			}
			System.out.println(convhull.getGeometryType()+"=====");
			shapeBuilder.setExtent(convhull);
		}else {
			shapeBuilder.setExtent(geom);
		}
		GeometryWrapper pointWrapper = GeometryWrapperFactory.createGeometry(shapeBuilder.getGeometry(), geometry.getSrsURI(), geometry.getGeometryDatatypeURI());
        return pointWrapper.asNodeValue();
        }catch(IllegalArgumentException e) {
        	return NodeValue.makeString("MULTIPOINT()^^http://www.opengis.net/ont/geosparql#wktLiteral");
        }
	  	
	}

}
