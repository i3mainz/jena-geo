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

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.SRSInfo;
import java.math.BigInteger;
import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

public class Transform extends FunctionBase2 {

    @Override
    public NodeValue exec(NodeValue arg0, NodeValue arg1) {

        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            SRSInfo srsInfo;
            if (arg1.isInteger()) {
                BigInteger srid = arg1.getInteger();
                srsInfo = new SRSInfo(srid.intValue());
            } else if (arg1.isIRI()) {
                String srsURI = arg1.getNode().getURI();
                srsInfo = new SRSInfo(srsURI);
            } else if (arg1.isString()) {
                String srsURI = arg1.getString();
                srsInfo = new SRSInfo(srsURI);
            } else {
                throw new ExprEvalException("Expected a SRID int, SRS URI as string or URI: " + arg1);
            }

            GeometryWrapper transGeometry = geometry.transform(srsInfo);
            return transGeometry.asNodeValue();
        } catch (DatatypeFormatException | FactoryException | MismatchedDimensionException | TransformException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
    }

}
