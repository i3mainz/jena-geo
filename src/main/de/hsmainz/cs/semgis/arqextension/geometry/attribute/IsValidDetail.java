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
package de.hsmainz.cs.semgis.arqextension.geometry.attribute;

import org.apache.jena.sparql.expr.NodeValue;

import java.util.Arrays;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.operation.valid.IsValidOp;
import org.locationtech.jts.operation.valid.TopologyValidationError;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class IsValidDetail extends FunctionBase1 {

    @Override
    public NodeValue exec(NodeValue arg0) {

        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            Object[] details = new Object[3];
            IsValidOp validOP = new IsValidOp(geometry.getParsingGeometry());
            GeometryFactory fac=new GeometryFactory();
            TopologyValidationError error = validOP.getValidationError();
            if (error != null) {
                details[0] = false ;
                details[1] = error.getMessage();
                details[2] = fac.createPoint(error.getCoordinate());
            } else {
                details[0] = true ;
                details[1] = "Valid Geometry";
            }
            return NodeValue.makeString(Arrays.toString(details));
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
    }

}
