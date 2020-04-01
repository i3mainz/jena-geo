package de.hsmainz.cs.semgis.arqextension.geometry.attribute;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Polygon;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class CompactnessRatio extends FunctionBase1{

	@Override
	public NodeValue exec(NodeValue v) {
		try {
            GeometryWrapper geometry = GeometryWrapper.extract(v);
            Geometry geom = geometry.getParsingGeometry();
    		if (geom instanceof Polygon) {
                final double circleRadius = Math.sqrt(geom.getArea() / Math.PI);
                final double circleCurcumference = 2 * Math.PI * circleRadius;
                return NodeValue.makeDouble(circleCurcumference / geom.getLength());
            }
    		return NodeValue.makeString("Geometry is not a Polygon");
        } catch (DatatypeFormatException ex) {
        	return NodeValue.makeString(ex.getMessage());
        }

	}

}
