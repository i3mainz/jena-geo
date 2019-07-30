package de.hsmainz.cs.semgis.arqextension.geometry;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Geometry;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

public class Union extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		try {
            GeometryWrapper geometry = GeometryWrapper.extract(v1);
            Geometry geom = geometry.getXYGeometry();
            GeometryWrapper geometry2 = GeometryWrapper.extract(v2);
            GeometryWrapper transGeom2 = geometry2.transform(geometry.getSRID());
            Geometry uniongeom=geom.union(transGeom2.getXYGeometry());
            GeometryWrapper unionWrapper = GeometryWrapperFactory.createGeometry(uniongeom, geometry.getSrsURI(), geometry.getGeometryDatatypeURI());
            return unionWrapper.asNodeValue();
        } catch (DatatypeFormatException | MismatchedDimensionException | TransformException | FactoryException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

}
