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

import org.apache.jena.graph.Node;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;

public class Units {

    public static final String uri = "http://www.opengis.net/def/uom/OGC/1.0/";

    protected static final Resource resource(String local) {
        return ResourceFactory.createResource(uri + local);
    }

    public static final Resource degree = resource("degree");
    public static final Resource metre = resource("metre");
    public static final Resource radian = resource("radian");
    public static final Resource unity = resource("unity");
    public static final Resource GridSpacing = resource("GridSpacing");

    public static class Nodes {

        public static final Node degree = Units.degree.asNode();
        public static final Node metre = Units.metre.asNode();
        public static final Node radian = Units.radian.asNode();
        public static final Node unity = Units.unity.asNode();
        public static final Node GridSpacing = Units.GridSpacing.asNode();
    }
}
