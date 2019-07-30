package de.hsmainz.cs.semgis.arqextension.point.attribute;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Coordinate;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; 
/**
 * Returns ZM (dimension semantic) flag of the geometries as a small int. Values are: 0=2d, 1=3dm, 2=3dz, 3=4d.
 *
 */
public class Zmflag extends FunctionBase1{

	@Override
	public NodeValue exec(NodeValue arg0) {
        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            Coordinate coord=geometry.getXYGeometry().getCoordinates()[0];
            if(Double.isNaN(coord.getM()) && Double.isNaN(coord.getZ())) {
            	return NodeValue.makeInteger(0);
            }else if(!Double.isNaN(coord.getM()) && Double.isNaN(coord.getZ())) {
            	return NodeValue.makeInteger(1);
            }else if(Double.isNaN(coord.getM()) && !Double.isNaN(coord.getZ())) {
            	return NodeValue.makeInteger(2);
            }else if(!Double.isNaN(coord.getM()) && !Double.isNaN(coord.getZ())) {
            	return NodeValue.makeInteger(3);
            }
            return NodeValue.nvNothing;
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

}
