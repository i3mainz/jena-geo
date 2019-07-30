package de.hsmainz.cs.semgis.arqextension.geometry.transform;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateXYM;
import org.locationtech.jts.geom.Geometry;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

/**
 * Force the geometries into XYZ mode.
 *
 */
public class Force3DM extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
        try {
            GeometryWrapper geometry = GeometryWrapper.extract(v);
            Geometry geom = geometry.getXYGeometry();
            List<Coordinate> newcoords=new ArrayList<Coordinate>();
            for(Coordinate coord:geom.getCoordinates()) {
            	newcoords.add(new CoordinateXYM(coord.x,coord.y,Double.isNaN(coord.getM())?0.:coord.getM()));
            }         
            return LiteralUtils.createGeometry(newcoords,geom.getGeometryType(),geometry).asNodeValue();                 
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

}
