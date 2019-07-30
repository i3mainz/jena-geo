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
import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import org.locationtech.jts.geom.Geometry;

/**
 * The concave hull of a geometry represents a possibly concave geometry that encloses all geometries within the set. You can think of it as shrink wrapping.
 *
 */
public class ConcaveHull extends FunctionBase3 {

    @Override
    public NodeValue exec(NodeValue arg0, NodeValue arg1, NodeValue arg2) {

        try {
            GeometryWrapper geom = GeometryWrapper.extract(arg0);
            float targetPercent = arg1.getFloat();
            boolean allowHoles = arg2.getBoolean();

            org.opensphere.geometry.algorithm.ConcaveHull hull = new org.opensphere.geometry.algorithm.ConcaveHull(geom.getXYGeometry(), targetPercent);

            Geometry concaveHull = hull.getConcaveHull();
            GeometryWrapper concaveWrapper = GeometryWrapperFactory.createGeometry(concaveHull, geom.getSrsURI(), geom.getGeometryDatatypeURI());
            return concaveWrapper.asNodeValue();
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }

    }

}
