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
package de.hsmainz.cs.semgis.arqextension.polygon.constructor;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; 
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.LinearRing;

/**
 * Creates a Polygon formed by the given shell. Input geometries must be closed LINESTRINGS.
 *
 */
public class MakePolygon extends FunctionBase1 {

    @Override
    public NodeValue exec(NodeValue arg0) {

        //TODO method can accept array of LinearRing holes but unclear how this would be passed in query.
        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            Geometry geom = geometry.getXYGeometry();
            if (geom instanceof LinearRing) {
                GeometryWrapper polygonWrapper = GeometryWrapperFactory.createPolygon((LinearRing) geom, geometry.getSrsURI(), geometry.getGeometryDatatypeURI());
                return polygonWrapper.asNodeValue();
            }
            if (geom instanceof LineString) {
                GeometryWrapper polygonWrapper = GeometryWrapperFactory.createPolygon((LinearRing) geom, geometry.getSrsURI(), geometry.getGeometryDatatypeURI());
                return polygonWrapper.asNodeValue();
            }
            return NodeValue.nvNothing;
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }

    }

}
