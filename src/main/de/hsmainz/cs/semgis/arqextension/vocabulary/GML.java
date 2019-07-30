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

public class GML {

    public static final String DATATYPE_URI = "http://www.opengis.net/ont/gml#";

    protected static final Resource resource(String local) {
        return ResourceFactory.createResource(DATATYPE_URI + local);
    }

    public static final Resource GMLLiteral = ResourceFactory.createProperty(Geo.uri + "gmlLiteral");

    public enum Type {

        Point, LineString, Curve, OrientableCurve, LineStringSegment, ArcString, Arc, Circle, ArcStringByBuldge, ArcByBuldge, ArcByCenterPoint, CircleByCenterPoint, CubicSpline, BSpline, Clothoid, CompositeCurve, CompositeSolid, CompositeSurface, Cone, Cylinder, Geodesic, GeodesicString, GriddedSurfacePatch, LinearRing, MultiCurve, MultiSolid, MultiSurface, OffsetCurve, OrientableSurface, PolygonPatch, PolyhedralSurface, Rectangle, Ring, Solid, Sphere, Surface, Tin, Triangle, TriangulatedSurface;

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
