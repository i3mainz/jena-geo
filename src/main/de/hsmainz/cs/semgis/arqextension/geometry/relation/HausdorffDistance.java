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
package de.hsmainz.cs.semgis.arqextension.geometry.relation;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.algorithm.distance.DiscreteHausdorffDistance;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

/**
 * Implements algorithm for computing a distance metric which can be thought of as the "Discrete Hausdorff Distance".
 *
 */
public class HausdorffDistance extends FunctionBase2 {

    @Override
    public NodeValue exec(NodeValue arg0, NodeValue arg1) {

        try {
            GeometryWrapper geom1 = GeometryWrapper.extract(arg0);
            GeometryWrapper geom2 = GeometryWrapper.extract(arg1);
            GeometryWrapper transGeom2 = geom2.transform(geom1.getSRID());

            DiscreteHausdorffDistance distance = new DiscreteHausdorffDistance(geom1.getXYGeometry(), transGeom2.getXYGeometry());
            return NodeValue.makeDouble(distance.distance());
        } catch (DatatypeFormatException | FactoryException | TransformException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
    }

}
