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
package de.hsmainz.cs.semgis.arqextension.point.constructor;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;
import io.github.galbiston.geosparql_jena.implementation.vocabulary.SRS_URI;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateXYM;

/**
 * Creates a point geometry with an x y and m coordinate.
 *
 */
public class MakePointM extends FunctionBase3 {

    @Override
    public NodeValue exec(NodeValue arg0, NodeValue arg1, NodeValue arg2) {

        Coordinate coord = new CoordinateXYM(arg0.getDouble(), arg1.getDouble(), arg2.getDouble());
        GeometryWrapper pointWrapper = GeometryWrapperFactory.createPoint(coord, SRS_URI.DEFAULT_WKT_CRS84, WKTDatatype.URI);
        return pointWrapper.asNodeValue();
    }

}
