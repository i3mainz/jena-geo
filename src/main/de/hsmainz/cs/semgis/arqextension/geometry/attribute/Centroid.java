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

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.apache.sis.coverage.grid.GridCoverage;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import de.hsmainz.cs.semgis.arqextension.util.Wrapper;

/**
 * Computes the geometric center of a geometry, or equivalently, the center of mass of the geometry as a POINT. 
 *
 */
public class Centroid extends FunctionBase1 {

    @Override
    public NodeValue exec(NodeValue arg0) {
    	Wrapper wrapper1=LiteralUtils.rasterOrVector(arg0);
		if(wrapper1 instanceof GeometryWrapper) {
        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            Geometry geom = geometry.getParsingGeometry();

            Point centroid = geom.getCentroid();
            GeometryWrapper centroidWrapper = GeometryWrapperFactory.createPoint(centroid.getCoordinate(), geometry.getSrsURI(), geometry.getGeometryDatatypeURI());

            return centroidWrapper.asNodeValue();
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
		}else if(wrapper1 instanceof CoverageWrapper) {
			GridCoverage raster=((CoverageWrapper)wrapper1).getGridGeometry();
			return GeometryWrapperFactory.createPoint(LiteralUtils.toGeometry(raster.getGridGeometry().getEnvelope()).getCentroid().getCoordinate(),WKTDatatype.URI).asNodeValue();
		}else {
			return NodeValue.nvEmptyString;
		}
    }
}
