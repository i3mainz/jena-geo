package de.hsmainz.cs.semgis.arqextension.geometry.relation;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.operation.distance3d.Distance3DOp;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

public class ClosestPoint3D extends FunctionBase2{

	@Override
	public NodeValue exec(NodeValue arg0, NodeValue arg1) {
        try {
            GeometryWrapper geom1 = GeometryWrapper.extract(arg0);
            GeometryWrapper geom2 = GeometryWrapper.extract(arg1);

            GeometryWrapper transGeom2 = geom2.transform(geom1.getSrsInfo());
            Distance3DOp distop = new Distance3DOp(geom1.getXYGeometry(), transGeom2.getXYGeometry());

            Coordinate[] coords = distop.nearestPoints();
            Coordinate coord = new Coordinate(coords[0].x, coords[0].y);

            GeometryWrapper pointWrapper = GeometryWrapperFactory.createPoint(coord, geom1.getSrsURI(), geom1.getGeometryDatatypeURI());

            return pointWrapper.asNodeValue();
        } catch (DatatypeFormatException | FactoryException | TransformException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}
	
	

}
