package de.hsmainz.cs.semgis.arqextension.geometry;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Geometry;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

public class ConvexHull extends FunctionBase1 {

    @Override
    public NodeValue exec(NodeValue arg0) {

        try {
            GeometryWrapper geom = GeometryWrapper.extract(arg0);
            org.locationtech.jts.algorithm.ConvexHull convex=new org.locationtech.jts.algorithm.ConvexHull(geom.getXYGeometry());

            Geometry convexHull = convex.getConvexHull();
            GeometryWrapper concaveWrapper = GeometryWrapperFactory.createGeometry(convexHull, geom.getSrsURI(), geom.getGeometryDatatypeURI());
            return concaveWrapper.asNodeValue();
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }

    }

}
