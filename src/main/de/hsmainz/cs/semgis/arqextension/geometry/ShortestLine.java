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
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.operation.distance.DistanceOp;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

/**
 * Returns the 2-dimensional shortest line between two geometries
 *
 */
public class ShortestLine extends FunctionBase2 {

    @Override
    public NodeValue exec(NodeValue arg0, NodeValue arg1) {

        try {
            GeometryWrapper geometry1 = GeometryWrapper.extract(arg0);
            GeometryWrapper geometry2 = GeometryWrapper.extract(arg1);

            GeometryWrapper transGeometry2 = geometry2.transform(geometry1.getSrsInfo());

            DistanceOp distop = new DistanceOp(geometry1.getXYGeometry(), transGeometry2.getXYGeometry());
            Coordinate[] coord = distop.nearestPoints();
            GeometryWrapper lineStringWrapper = GeometryWrapperFactory.createLineString(coord, geometry1.getSrsURI(), geometry1.getGeometryDatatypeURI());

            return lineStringWrapper.asNodeValue();
        } catch (DatatypeFormatException | FactoryException | TransformException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
    }

}
