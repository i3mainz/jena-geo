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
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.MultiPoint;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;

/**
 * Returns TRUE if the LINESTRING's start and end points are coincident. For Polyhedral surface is closed (volumetric). 
 *
 */
public class IsClosed extends FunctionBase1 {

    @Override
    public NodeValue exec(NodeValue arg0) {

        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            Geometry geom = geometry.getXYGeometry();
            if(geom instanceof Point)
            	return NodeValue.TRUE;
            if(geom instanceof MultiPoint) {
            	LineString line=(LineString) GeometryWrapperFactory.createLineString(((MultiPoint)geom).getCoordinates(),  "<http://www.opengis.net/def/crs/EPSG/0/" + geom.getSRID() + ">", WKTDatatype.URI).getXYGeometry();
                boolean isClosed = line.isClosed();
                return NodeValue.makeNodeBoolean(isClosed);            	
            }
            if (geom instanceof LineString) {
                boolean isClosed = ((LineString) geom).isClosed();
                return NodeValue.makeNodeBoolean(isClosed);
            }
            if (geom instanceof Polygon) {
                return NodeValue.TRUE;
            }

            return NodeValue.FALSE;
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
    }

}
