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
import java.util.List;

import org.apache.jena.atlas.lib.Lib;
import org.apache.jena.query.QueryBuildException;
import org.apache.jena.sparql.expr.ExprList;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateXY;
import org.locationtech.jts.geom.CoordinateXYZM;

/**
 * Creates a 2D, 3DZ or 4D point geometry.
 *
 */
public class MakePoint extends FunctionBase {

    @Override
    public NodeValue exec(List<NodeValue> args) {

        Coordinate coord;
        switch (args.size()) {
            case 3:
                coord = new Coordinate(args.get(0).getDouble(), args.get(1).getDouble(), args.get(2).getDouble());
                break;
            case 4:
                coord = new CoordinateXYZM(args.get(0).getDouble(), args.get(1).getDouble(), args.get(2).getDouble(), args.get(3).getDouble());
                break;
            default:
                coord = new CoordinateXY(args.get(0).getDouble(), args.get(1).getDouble());

        }

        GeometryWrapper pointWrapper = GeometryWrapperFactory.createPoint(coord, SRS_URI.DEFAULT_WKT_CRS84, WKTDatatype.URI);

        return pointWrapper.asNodeValue();
    }

    @Override
    public void checkBuild(String uri, ExprList args) {
        if (args.size() < 2 || args.size() > 4) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' takes two, three or four arguments");
        }
    }

}
