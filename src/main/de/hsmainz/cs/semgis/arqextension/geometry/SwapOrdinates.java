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

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateXYM;
import org.locationtech.jts.geom.CoordinateXYZM;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;

public class SwapOrdinates extends FunctionBase2 {

    @Override
    public NodeValue exec(NodeValue arg0, NodeValue arg1) {

        try {
            GeometryWrapper geom = GeometryWrapper.extract(arg0);
            String ords = arg1.getString();
            List<Coordinate> newcoords=new ArrayList<Coordinate>();
            if(ords.equalsIgnoreCase("xy")) {
            	for(Coordinate coord:geom.getXYGeometry().getCoordinates()) {
            		if(coord instanceof CoordinateXYM) {
                		newcoords.add(new CoordinateXYM(coord.getY(),coord.getX(),coord.getM()));
            		}else if(coord instanceof CoordinateXYZM) {
            			newcoords.add(new CoordinateXYZM(coord.getY(),coord.getX(),coord.getZ(),coord.getM()));
            		}else {
                		newcoords.add(new Coordinate(coord.getY(),coord.getX()));            			
            		}
            	}
            }else if(ords.equalsIgnoreCase("xz")) {
               	for(Coordinate coord:geom.getXYGeometry().getCoordinates()) {
            		if(coord instanceof CoordinateXYM) {
                		throw new RuntimeException("Coordinates do not contain a Z value");
            		}else if(coord instanceof CoordinateXYZM) {
            			newcoords.add(new CoordinateXYZM(coord.getZ(),coord.getY(),coord.getX(),coord.getM()));
            		}else {
            			newcoords.add(new Coordinate(coord.getZ(),coord.getY(),coord.getX()));          			
            		}
            	}
            }else if(ords.equalsIgnoreCase("yz")) {
               	for(Coordinate coord:geom.getXYGeometry().getCoordinates()) {
            		if(coord instanceof CoordinateXYM) {
                		throw new RuntimeException("Coordinates do not contain a Z value");
            		}else if(coord instanceof CoordinateXYZM) {
            			newcoords.add(new CoordinateXYZM(coord.getX(),coord.getZ(),coord.getY(),coord.getM()));
            		}else {
            			newcoords.add(new Coordinate(coord.getX(),coord.getZ(),coord.getY()));         			
            		}
            	}
            }else if(ords.equalsIgnoreCase("xm")) {
               	for(Coordinate coord:geom.getXYGeometry().getCoordinates()) {
            		if(coord instanceof CoordinateXYM) {
            			newcoords.add(new CoordinateXYM(coord.getM(),coord.getY(),coord.getX()));
            		}else if(coord instanceof CoordinateXYZM) {
            			newcoords.add(new CoordinateXYZM(coord.getM(),coord.getY(),coord.getZ(),coord.getX()));
            		}else {
                		throw new RuntimeException("Coordinates do not contain a M value");          			
            		}
            	}
            }else if(ords.equalsIgnoreCase("ym")) {
               	for(Coordinate coord:geom.getXYGeometry().getCoordinates()) {
            		if(coord instanceof CoordinateXYM) {
            			newcoords.add(new CoordinateXYM(coord.getX(),coord.getM(),coord.getY()));
            		}else if(coord instanceof CoordinateXYZM) {
            			newcoords.add(new CoordinateXYZM(coord.getX(),coord.getM(),coord.getZ(),coord.getY()));
            		}else {
                		throw new RuntimeException("Coordinates do not contain a M value");          			
            		}
            	}
            }else if(ords.equalsIgnoreCase("zm")) {
               	for(Coordinate coord:geom.getXYGeometry().getCoordinates()) {
            		if(coord instanceof CoordinateXYM) {
            			throw new RuntimeException("Coordinates do not contain a M and Z value");      
            		}else if(coord instanceof CoordinateXYZM) {
            			newcoords.add(new CoordinateXYZM(coord.getX(),coord.getY(),coord.getM(),coord.getZ()));
            		}else {
                		throw new RuntimeException("Coordinates do not contain a M and Z value");          			
            		}
            	}
            }else {
            	throw new RuntimeException("Invalid second argument"); 
            }
            return LiteralUtils.createGeometry(newcoords, geom.getGeometryType(), geom).asNodeValue();
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
    }

}
