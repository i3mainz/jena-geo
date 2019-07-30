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
package de.hsmainz.cs.semgis.arqextension.linestring;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.SRSInfo;
import io.github.galbiston.geosparql_jena.implementation.great_circle.GreatCircleDistance;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.PrecisionModel;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.util.FactoryException;

public class Segmentize extends FunctionBase2 {

    @Override
    public NodeValue exec(NodeValue arg0, NodeValue arg1) {

        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            double segmentLength = arg1.getDouble();

            List<LineString> linestrings = createSegments(geometry, segmentLength);
            return GeometryWrapperFactory.createMultiLineString(linestrings, geometry.getSrsURI(),geometry.getGeometryDatatypeURI()).asNodeValue();
        } catch (DatatypeFormatException | FactoryException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
    }

    public List<LineString> createSegments(GeometryWrapper geometry, double segmentLength) throws NoSuchAuthorityCodeException, FactoryException {

        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(PrecisionModel.FLOATING));

        Geometry track = geometry.getXYGeometry();
        SRSInfo srsInfo = geometry.getSrsInfo();

        List<Coordinate> coordinates = new ArrayList<>(track.getCoordinates().length);
        Collections.addAll(coordinates, track.getCoordinates());

        double accumulatedLength = 0;
        List<Coordinate> lastSegment = new ArrayList<>();
        List<LineString> segments = new ArrayList<>();
        Iterator<Coordinate> itCoordinates = coordinates.iterator();

        for (int i = 0; itCoordinates.hasNext() && i < coordinates.size() - 1; i++) {
            Coordinate c1 = coordinates.get(i);
            Coordinate c2 = coordinates.get(i + 1);

            lastSegment.add(c1);

            double length;
            //Check for geographic or planar distance.
            //Does the rest of the calculation take into consideration geographic is non-linear in X/Y relationship?
            if (srsInfo.isGeographic()) {
                length = GreatCircleDistance.haversineFormula(c1, c2);
            } else {
                length = c1.distance(c2);
            }

            if (length + accumulatedLength >= segmentLength) {
                double offsetLength = segmentLength - accumulatedLength;
                double ratio = offsetLength / length;
                double dx = c2.x - c1.x;
                double dy = c2.y - c1.y;

                Coordinate segmentationPoint = new Coordinate(c1.x + (dx * ratio), c1.y + (dy * ratio));

                lastSegment.add(segmentationPoint); // Last point of the segment is the segmentation point
                segments.add(geometryFactory.createLineString(lastSegment.toArray(new Coordinate[lastSegment.size()])));

                lastSegment = new ArrayList<>(); // Resets the variable since a new segment will be built
                accumulatedLength = 0D;
                coordinates.add(i + 1, segmentationPoint);
            } else {
                accumulatedLength += length;
            }
        }

        lastSegment.add(coordinates.get(coordinates.size() - 1)); // Because the last one is never added in the loop above
        segments.add(geometryFactory.createLineString(lastSegment.toArray(new Coordinate[lastSegment.size()])));

        return segments;
    }

}
