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
import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.operation.buffer.BufferParameters;
import org.locationtech.jts.operation.buffer.OffsetCurveBuilder;

public class OffsetCurve extends FunctionBase3 {

    @Override
    public NodeValue exec(NodeValue arg0, NodeValue arg1, NodeValue arg2) {

        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            Geometry geom=geometry.getXYGeometry();
            Double distance=arg1.getDouble();
            BufferParameters bufParams = new BufferParameters();
            OffsetCurveBuilder ocb = new OffsetCurveBuilder(
                geom.getFactory().getPrecisionModel(), bufParams
                );
            Coordinate[] pts = ocb.getOffsetCurve(geom.getCoordinates(), distance);
            Geometry curve = geom.getFactory().createLineString(pts);
            GeometryWrapper pointWrapper = GeometryWrapperFactory.createGeometry(curve, geometry.getSrsURI(), geometry.getGeometryDatatypeURI());
            return pointWrapper.asNodeValue();
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
    }

}
