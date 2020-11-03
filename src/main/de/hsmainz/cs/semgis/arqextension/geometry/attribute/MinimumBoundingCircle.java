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
import org.locationtech.jts.geom.Geometry;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import de.hsmainz.cs.semgis.arqextension.util.Wrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.function.FunctionBase1;
import org.geotoolkit.coverage.grid.GridCoverage2D;

/**
 * Returns the smallest circle polygon that can fully contain a geometry. Default uses 48 segments per quarter circle.
 *
 */
public class MinimumBoundingCircle extends FunctionBase1 {

    @Override
    public NodeValue exec(NodeValue arg0) {
    	Wrapper wrapper1=LiteralUtils.rasterOrVector(arg0);
    	if(wrapper1 instanceof GeometryWrapper) {
        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            Geometry geom = geometry.getParsingGeometry();
            org.locationtech.jts.algorithm.MinimumBoundingCircle minCircle = new org.locationtech.jts.algorithm.MinimumBoundingCircle(geom);
            GeometryWrapper minCircleWrapper = GeometryWrapperFactory.createGeometry(minCircle.getCircle(), geometry.getSrsURI(), geometry.getGeometryDatatypeURI());
            return minCircleWrapper.asNodeValue();
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
    	}else if(wrapper1 instanceof CoverageWrapper) {
   		 CoverageWrapper covwrap = CoverageWrapper.extract(arg0);
         GridCoverage2D cov = covwrap.getXYGeometry();
         Geometry geom=LiteralUtils.toGeometry(cov.getGridGeometry().getEnvelope());
         org.locationtech.jts.algorithm.MinimumBoundingCircle minCircle = new org.locationtech.jts.algorithm.MinimumBoundingCircle(geom);
         GeometryWrapper minCircleWrapper = GeometryWrapperFactory.createGeometry(minCircle.getCircle(),  WKTDatatype.URI);
         return minCircleWrapper.asNodeValue();
	}else {
		throw new ExprEvalException("No geometry or coverage literal");
	}
    }

}
