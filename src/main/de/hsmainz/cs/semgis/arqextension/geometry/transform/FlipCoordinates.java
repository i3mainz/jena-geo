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

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateXYM;
import org.locationtech.jts.geom.CoordinateXYZM;
import org.locationtech.jts.geom.Geometry;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;

/**
 * Returns a version of the given geometry with X and Y axis flipped.
 *
 */
public class FlipCoordinates extends FunctionBase1 {

    @Override
    public NodeValue exec(NodeValue arg0) {
        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            Geometry geom = geometry.getParsingGeometry();
            List<Coordinate> newcoords=new ArrayList<Coordinate>();
            	for(Coordinate coord:geom.getCoordinates()) {
            		if(coord instanceof CoordinateXYM) {
                		newcoords.add(new CoordinateXYM(coord.getY(),coord.getX(),coord.getM()));
            		}else if(coord instanceof CoordinateXYZM) {
            			newcoords.add(new CoordinateXYZM(coord.getY(),coord.getX(),coord.getZ(),coord.getM()));
            		}else {
                		newcoords.add(new Coordinate(coord.getX(),coord.getY()));            			
            		}
            	}
            	 return LiteralUtils.createGeometry(newcoords, geometry.getGeometryType(), geometry).asNodeValue();
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
    }
    
}
