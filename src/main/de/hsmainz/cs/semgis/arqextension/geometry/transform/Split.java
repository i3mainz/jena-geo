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

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.util.LineStringExtracter;
import org.locationtech.jts.operation.polygonize.Polygonizer;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

public class Split extends FunctionBase2 {

    @Override
    public NodeValue exec(NodeValue arg0, NodeValue arg1) {

        try {
            GeometryWrapper input = GeometryWrapper.extract(arg0);
            GeometryWrapper blade = GeometryWrapper.extract(arg1);
            GeometryWrapper transBlade = blade.transform(input.getSrsInfo());
            if(input.getXYGeometry().getGeometryType().equalsIgnoreCase("Polygon")
            		&& transBlade.getXYGeometry().getGeometryType().equalsIgnoreCase("LineString")) {
            	Geometry result=splitPolygon(input.getXYGeometry(), transBlade.getXYGeometry());
            	return GeometryWrapperFactory.createGeometry(result, input.getSrsURI(), input.getGeometryDatatypeURI()).asNodeValue();
            }else if(input.getXYGeometry().getGeometryType().equalsIgnoreCase("LineString") 
            		&& input.getXYGeometry().getGeometryType().equalsIgnoreCase("Point")) {
            	throw new ExprEvalException("Constellation of input geometries is not supported by this Split operator");	
            }
            throw new ExprEvalException("Constellation of input geometries is not supported by this Split operator");
        } catch (DatatypeFormatException | FactoryException | TransformException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
    }
    
    
    public static Geometry polygonize(Geometry geometry) {
        List lines = LineStringExtracter.getLines(geometry);
        Polygonizer polygonizer = new Polygonizer();
        polygonizer.add(lines);
        Collection polys = polygonizer.getPolygons();
        Polygon[] polyArray = GeometryFactory.toPolygonArray(polys);
        return geometry.getFactory().createGeometryCollection(polyArray);
    }

    public static Geometry splitPolygon(Geometry poly, Geometry line) {
        Geometry nodedLinework = poly.getBoundary().union(line);
        Geometry polys = polygonize(nodedLinework);

        // Only keep polygons which are inside the input
        List output = new ArrayList();
        for (int i = 0; i < polys.getNumGeometries(); i++) {
            Polygon candpoly = (Polygon) polys.getGeometryN(i);
            if (poly.contains(candpoly.getInteriorPoint())) {
                output.add(candpoly);
            }
        }
        return poly.getFactory().createGeometryCollection(GeometryFactory.toGeometryArray(output));
    }

}
