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
package de.hsmainz.cs.semgis.arqextension.geometry.transform;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.spatial.filter_functions.FunctionBase5;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.util.AffineTransformation;

public class TransScale extends FunctionBase5 {

    @Override
    public NodeValue exec(NodeValue arg0, NodeValue arg1, NodeValue arg2, NodeValue arg3, NodeValue arg4) {

        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            Geometry geom = geometry.getXYGeometry();
            double deltaX = arg1.getDouble();
            double deltaY = arg2.getDouble();
            double xFactor = arg3.getDouble();
            double yFactor = arg4.getDouble();

            //Translate and scale
            AffineTransformation transform = new AffineTransformation();
            transform.translate(deltaX, deltaY);
            transform.scale(xFactor, yFactor);

            Geometry transGeom = transform.transform(geom);
            GeometryWrapper transWrapper = GeometryWrapperFactory.createGeometry(transGeom, geometry.getSrsURI(), geometry.getGeometryDatatypeURI());
            return transWrapper.asNodeValue();
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
    }

}
