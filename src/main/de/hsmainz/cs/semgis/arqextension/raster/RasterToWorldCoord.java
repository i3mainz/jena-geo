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
package de.hsmainz.cs.semgis.arqextension.raster;

import io.github.galbiston.geosparql_jena.implementation.CoverageWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

import java.awt.geom.Point2D;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.locationtech.jts.geom.CoordinateXY;
import org.opengis.referencing.operation.TransformException;

/**
 * Returns the raster's upper left corner as geometric X and Y (longitude and latitude) given a column and row. Column and row starts at 1.
 *
 */
public class RasterToWorldCoord extends FunctionBase3 {

	@Override
	public NodeValue exec(NodeValue v,NodeValue v1,NodeValue v2) {
		CoverageWrapper wrapper=CoverageWrapper.extract(v);
		GridCoverage2D raster=wrapper.getXYGeometry();	
		Integer column = v1.getInteger().intValue();
        Integer row = v2.getInteger().intValue();
        try {
            Point2D position = raster.getGridGeometry().getGridToCRS2D().transform(new org.geotoolkit.coverage.grid.GridCoordinates2D(column, row),null);
            CoordinateXY coord = new CoordinateXY(position.getX(), position.getY());
            GeometryWrapper pointWrapper = GeometryWrapperFactory.createPoint(coord, wrapper.getSrsURI(), wrapper.getRasterDatatypeURI());
            return pointWrapper.asNodeValue();
        } catch (TransformException e) {
            return NodeValue.nvNothing;
        }
	}

}
