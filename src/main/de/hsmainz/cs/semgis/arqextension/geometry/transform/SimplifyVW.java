package de.hsmainz.cs.semgis.arqextension.geometry.transform;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.simplify.VWSimplifier;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

/**
 * Returns a "simplified" version of the given geometry using the Visvalingam-Whyatt algorithm
 *
 */
public class SimplifyVW extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue arg0,NodeValue arg1) {
        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            Geometry geom = geometry.getXYGeometry();
            double tolerance = arg1.getDouble();

            //Simplify
            VWSimplifier simplifier = new VWSimplifier(geom);      
            simplifier.setDistanceTolerance(tolerance);

            Geometry simpleGeom = simplifier.getResultGeometry();
            GeometryWrapper simpleWrapper = GeometryWrapperFactory.createGeometry(simpleGeom, geometry.getSrsURI(), geometry.getGeometryDatatypeURI());
            return simpleWrapper.asNodeValue();

        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

}
