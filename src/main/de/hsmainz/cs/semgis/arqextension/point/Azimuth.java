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
package de.hsmainz.cs.semgis.arqextension.point;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import java.awt.geom.Point2D;
import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.geotoolkit.referencing.GeodeticCalculator;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

/**
 * Returns the north-based azimuth as the angle in radians measured clockwise from the vertical on pointA to pointB.
 *
 */
public class Azimuth extends FunctionBase2 {

    @Override
    public NodeValue exec(NodeValue arg0, NodeValue argeom1) {

        try {
            GeometryWrapper geometry1 = GeometryWrapper.extract(arg0);
            GeometryWrapper geometry2 = GeometryWrapper.extract(argeom1);
            GeometryWrapper transGeometry2 = geometry2.transform(geometry1.getSrsInfo());

            Geometry geom1 = geometry1.getXYGeometry();
            Geometry geom2 = transGeometry2.getXYGeometry();

            GeodeticCalculator calc = new GeodeticCalculator();

            if (geom1 instanceof Point && geom2 instanceof Point) {
                Point2D point1 = new java.awt.Point();
                point1.setLocation(geom1.getCoordinate().x, geom1.getCoordinate().y);
                Point2D point2 = new java.awt.Point();
                point2.setLocation(geom2.getCoordinate().x, geom2.getCoordinate().y);
                calc.setStartingGeographicPoint(point1);
                calc.setDestinationGeographicPoint(point2);
                return NodeValue.makeDouble(calc.getAzimuth());
            }else {
                Point2D point1 = new java.awt.Point();
                point1.setLocation(geom1.getCentroid().getCoordinate().x, geom1.getCentroid().getCoordinate().y);
                Point2D point2 = new java.awt.Point();
                point2.setLocation(geom2.getCentroid().getCoordinate().x, geom2.getCentroid().getCoordinate().y);
                calc.setStartingGeographicPoint(point1);
                calc.setDestinationGeographicPoint(point2);
                return NodeValue.makeDouble(calc.getAzimuth());
            }

        } catch (DatatypeFormatException | FactoryException | MismatchedDimensionException | TransformException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }

    }

}
