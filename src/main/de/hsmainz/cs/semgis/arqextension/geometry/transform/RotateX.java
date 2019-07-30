package de.hsmainz.cs.semgis.arqextension.geometry.transform;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.geotoolkit.geometry.jts.JTS;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.util.AffineTransformation;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

public class RotateX extends FunctionBase2 {

    @Override
    public NodeValue exec(NodeValue arg0, NodeValue arg1) {

        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            Geometry geom = geometry.getXYGeometry();

            float rotRadians = arg1.getFloat();
            AffineTransformation trans = new AffineTransformation();
            trans.rotate(rotRadians);
      

            GeometryWrapper transformGeomWrapper = GeometryWrapperFactory.createGeometry(transformGeom, geometry.getSrsURI(), geometry.getGeometryDatatypeURI());

            return transformGeomWrapper.asNodeValue();

        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
    }
}
