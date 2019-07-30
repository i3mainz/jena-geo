package de.hsmainz.cs.semgis.arqextension.geometry;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.operation.distance3d.Distance3DOp;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

public class ShortestLine3D extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue arg0, NodeValue arg1) {
		 try {
	            GeometryWrapper geometry1 = GeometryWrapper.extract(arg0);
	            GeometryWrapper geometry2 = GeometryWrapper.extract(arg1);

	            GeometryWrapper transGeometry2 = geometry2.transform(geometry1.getSrsInfo());

	            Distance3DOp distop = new Distance3DOp(geometry1.getXYGeometry(), transGeometry2.getXYGeometry());
	            Coordinate[] coord = distop.nearestPoints();
	            GeometryWrapper lineStringWrapper = GeometryWrapperFactory.createLineString(coord, geometry1.getSrsURI(), geometry1.getGeometryDatatypeURI());

	            return lineStringWrapper.asNodeValue();
	        } catch (Exception ex) {
	        	return NodeValue.makeString(ex.getMessage());
	            //throw new ExprEvalException(ex.getMessage(), ex);
	        }
	}

}
