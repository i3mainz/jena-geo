/** *****************************************************************************
 * Copyright (c) 2017 Timo Homburg, i3Mainz.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the BSD License
 * which accompanies this distribution, and is available at
 * https://directory.fsf.org/wiki/License:BSD_4Clause
 *
 * This project extends work by Ian Simmons who developed the Parliament Triple Store.
 * http://parliament.semwebcentral.org and published his work und BSD License as well.
 *
 *
 ****************************************************************************** */
package de.hsmainz.cs.semgis.arqextension.geometry;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import java.math.BigInteger;
import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.triangulate.DelaunayTriangulationBuilder;

public class DelaunayTriangles extends FunctionBase3 {

    @Override
    public NodeValue exec(NodeValue arg0, NodeValue arg1, NodeValue arg2) {

        try {
            GeometryWrapper geom = GeometryWrapper.extract(arg0);
            float tolerance = arg1.getFloat();
            BigInteger flags = arg2.getInteger();

            DelaunayTriangulationBuilder builder = new DelaunayTriangulationBuilder();
            builder.setTolerance(tolerance);
            Geometry triangles = builder.getTriangles(new GeometryFactory());

            GeometryWrapper trianglesWrapper = GeometryWrapperFactory.createGeometry(triangles, geom.getSrsURI(), geom.getGeometryDatatypeURI());
            return trianglesWrapper.asNodeValue();

        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }

    }

}
