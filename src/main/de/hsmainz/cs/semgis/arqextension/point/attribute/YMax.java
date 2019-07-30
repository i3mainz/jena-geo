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
package de.hsmainz.cs.semgis.arqextension.point.attribute;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; 
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;

/**
 * Returns Y maxima of a bounding box 2d or 3d or a geometry.
 *
 */
public class YMax extends FunctionBase1 {

    @Override
    public NodeValue exec(NodeValue arg0) {
        GeometryWrapper geometry = GeometryWrapper.extract(arg0);
        Geometry geo=geometry.getXYGeometry();
        Double maxY=0.;
        for(Coordinate coord:geo.getCoordinates()) {
        	if(maxY<coord.getY()) {
        		maxY=coord.getY();
        	}
        }
        return NodeValue.makeDouble(maxY);
    }

}
