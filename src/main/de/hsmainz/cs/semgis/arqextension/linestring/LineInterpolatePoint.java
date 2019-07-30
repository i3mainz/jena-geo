package de.hsmainz.cs.semgis.arqextension.linestring;

import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.linearref.LengthLocationMap;
import org.locationtech.jts.linearref.LinearLocation;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

/**
 * Returns a point interpolated along a line. Second argument is a float8 between 0 and 1 representing fraction of total length of linestring the point has to be located.
 *
 */
public class LineInterpolatePoint extends FunctionBase2{

	@Override
	public NodeValue exec(NodeValue arg0, NodeValue arg1) {
		GeometryWrapper geom1 = GeometryWrapper.extract(arg0);
        Double a_fraction = arg1.getDouble();
        if(geom1.getXYGeometry().getGeometryType().equals("LineString")) {
        	Double length=geom1.getXYGeometry().getLength();
        	Double fractionlength=a_fraction*length;
            LengthLocationMap locmap=new LengthLocationMap(geom1.getXYGeometry());
            LinearLocation linloc=locmap.getLocation(fractionlength);
            Coordinate coord=linloc.getCoordinate(geom1.getXYGeometry());
            return GeometryWrapperFactory.createPoint(coord, WKTDatatype.URI).asNodeValue();
        }
        throw new ExprEvalException("Given Geometry is not a LineString");
	}

}
