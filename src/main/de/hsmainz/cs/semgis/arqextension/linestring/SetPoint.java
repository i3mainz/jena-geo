package de.hsmainz.cs.semgis.arqextension.linestring;

import java.math.BigInteger;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Point;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class SetPoint extends FunctionBase3 {

	@Override
	public NodeValue exec(NodeValue arg0, NodeValue arg1, NodeValue arg2) {
			try {
	            GeometryWrapper geom1 = GeometryWrapper.extract(arg0);
	            GeometryWrapper geom2 = GeometryWrapper.extract(arg2);
	            GeometryWrapper transGeom2 = geom2.transform(geom1.getSRID());
	            BigInteger zerobasedposition=arg1.getInteger();
	            if (geom1.getParsingGeometry() instanceof LineString && transGeom2.getParsingGeometry() instanceof Point) {
		            Coordinate[] coords=geom1.getParsingGeometry().getCoordinates();
		            Coordinate[] newcoords=coords;
		            newcoords[zerobasedposition.intValue()]=geom2.getParsingGeometry().getCoordinate();
		            GeometryWrapper lineStringWrapper = GeometryWrapperFactory.createLineString(newcoords, "<http://www.opengis.net/def/crs/EPSG/0/"+geom1.getSRID()+">", WKTDatatype.URI);
	                return lineStringWrapper.asNodeValue();
	            }
	            throw new ExprEvalException("First argument is not a LineString or third argument is not a point", null);
	        } catch (DatatypeFormatException | FactoryException | TransformException ex) {
	            throw new ExprEvalException(ex.getMessage(), ex);
	        }
	}

}
