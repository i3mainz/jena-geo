package de.hsmainz.cs.semgis.arqextension.linestring;

import java.util.Arrays;
import java.util.List;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Point;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

/**
 * Add a point to a LineString.
 *
 */
public class AddPoint extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue arg0, NodeValue arg1) {
		try {
            GeometryWrapper geom1 = GeometryWrapper.extract(arg0);
            GeometryWrapper geom2 = GeometryWrapper.extract(arg1);
            GeometryWrapper transGeom2 = geom2.transform(geom1.getSRID());
            if (geom1.getParsingGeometry() instanceof LineString && geom2.getParsingGeometry() instanceof Point) {
            	Coordinate[] coords=geom1.getParsingGeometry().getCoordinates();
                Point point = ((Point) transGeom2.getParsingGeometry());
                List<Coordinate> newcoords=Arrays.asList(coords);
                newcoords.add(point.getCoordinate());
                GeometryWrapper lineStringWrapper = GeometryWrapperFactory.createLineString(newcoords, "<http://www.opengis.net/def/crs/EPSG/0/"+geom1.getSRID()+">", WKTDatatype.URI);
                return lineStringWrapper.asNodeValue();
            }
            throw new ExprEvalException("First argument is not a LineString or second argument is not a point", null);
        } catch (DatatypeFormatException | FactoryException | TransformException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

}
