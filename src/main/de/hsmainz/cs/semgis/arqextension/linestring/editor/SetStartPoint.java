package de.hsmainz.cs.semgis.arqextension.linestring.editor;

import java.math.BigInteger;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Point;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class SetStartPoint extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {	
		try {
            GeometryWrapper geom1 = GeometryWrapper.extract(v1);
            GeometryWrapper geom2 = GeometryWrapper.extract(v2);
            GeometryWrapper transGeom2 = geom2.transform(geom1.getSRID());
            if (geom1.getParsingGeometry() instanceof LineString && transGeom2.getParsingGeometry() instanceof Point) {
                Coordinate[] coords=geom1.getParsingGeometry().getCoordinates();
                Coordinate[] newcoords=coords;
                newcoords[0]=transGeom2.getXYGeometry().getCoordinate();
	            GeometryWrapper lineStringWrapper = GeometryWrapperFactory.createLineString(newcoords, "<http://www.opengis.net/def/crs/EPSG/0/"+geom1.getSRID()+">", WKTDatatype.URI);
                return lineStringWrapper.asNodeValue();
            }
            throw new ExprEvalException("First argument is not a LineString or third argument is not a point", null);
        } catch (DatatypeFormatException | FactoryException | TransformException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

}
