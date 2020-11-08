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

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.apache.sis.coverage.grid.GridCoverage;
import org.locationtech.jts.geom.Geometry;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import de.hsmainz.cs.semgis.arqextension.util.Wrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

/**
 * Returns the area of the surface if it is a Polygon or MultiPolygon. For geometry, a 2D Cartesian area is determined with units specified by the SRID. For geography, area is determined on a curved surface with units in square meters.
 *
 */
public class Area extends FunctionBase1 {

    @Override
    public NodeValue exec(NodeValue arg0) {
    	Wrapper wrapper1=LiteralUtils.rasterOrVector(arg0);
    	if(wrapper1 instanceof GeometryWrapper) {
        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            Geometry geom = geometry.getParsingGeometry();

            return NodeValue.makeDouble(geom.getArea());
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
    	}else if(wrapper1 instanceof CoverageWrapper) {
    		 CoverageWrapper covwrap = CoverageWrapper.extract(arg0);
             GridCoverage cov = covwrap.getXYGeometry();
             return NodeValue.makeDouble(LiteralUtils.toGeometry(cov.getGridGeometry().getEnvelope()).getArea());
    	}else {
    		return NodeValue.makeDouble(0.);
    	}
    }

}
