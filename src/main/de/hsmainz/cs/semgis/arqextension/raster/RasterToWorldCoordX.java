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

import java.awt.geom.Point2D;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import org.geotoolkit.coverage.grid.GridCoordinates2D;
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.opengis.referencing.operation.TransformException;

/**
 * Returns the geometric X coordinate upper left of a raster, column and row. Numbering of columns and rows starts at 1.
 *
 */
public class RasterToWorldCoordX extends FunctionBase3 {

	
	@Override
	public NodeValue exec(NodeValue v,NodeValue v1,NodeValue v2) {
		CoverageWrapper wrapper=CoverageWrapper.extract(v);
		GridCoverage2D raster=wrapper.getXYGeometry();	
		Integer column = v1.getInteger().intValue();
        Integer row = v2.getInteger().intValue();
        try {
        	raster.getGridGeometry().getGridToCRS2D().transform(new GridCoordinates2D(0, 0),null);
            Point2D position = raster.getGridGeometry().getGridToCRS2D().transform(new GridCoordinates2D(column, row),null);
            return NodeValue.makeDouble(position.getX());
        } catch (TransformException e) {
            return NodeValue.nvNothing;
        }
	}

}
