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
package de.hsmainz.cs.semgis.arqextension.vocabulary;

import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;

public class WKT {

    public static final String DATATYPE_URI = "http://www.opengis.net/ont/sf#";
//   public static final String GEOMETRY_TYPE_URI = "http://www.opengis.net/def/geometryType/OGC-SF/1.0/";

    protected static final Resource resource(String local) {
        return ResourceFactory.createResource(DATATYPE_URI + local);
    }

    public static final Resource WKTLiteral = ResourceFactory.createProperty(Geo.uri + "wktLiteral");

    public static final Resource Point = resource("Point");
    public static final Resource Curve = resource("Curve");
    public static final Resource Surface = resource("Surface");
    public static final Resource GeometryCollection = resource("GeometryCollection");
    public static final Resource LineString = resource("LineString");
    public static final Resource Polygon = resource("Polygon");
    public static final Resource PolyhedralSurface = resource("PolyhedralSurface");
    public static final Resource MultiSurface = resource("MultiSurface");
    public static final Resource MultiCurve = resource("MultiCurve");
    public static final Resource MultiPoint = resource("MultiPoint");
    public static final Resource Line = resource("Line");
    public static final Resource LinearRing = resource("LinearRing");
    public static final Resource MultiPolygon = resource("MultiPolygon");
    public static final Resource MultiLineString = resource("MultiLineString");

    public enum Type {
        Point, Curve, Surface, GeometryCollection, LineString, Polygon, PolyhedralSurface,
        MultiSurface, MultiCurve, MultiPoint, Line, LinearRing, MultiPolygon, MultiLineString;

        public Resource asResource() {
            return resource(name());
        }

        public String getURI() {
            return asResource().getURI();
        }

        @Override
        public String toString() {
            return getURI();
        }

        public static Type valueOfURI(String uri) {
            for (Type t : Type.values()) {
                if (t.getURI().equals(uri)) {
                    return t;
                }
            }
            return null;
        }
    }
}
