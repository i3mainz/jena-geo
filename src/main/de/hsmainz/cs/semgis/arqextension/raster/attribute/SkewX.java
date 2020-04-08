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
package de.hsmainz.cs.semgis.arqextension.raster.attribute;

import java.awt.geom.AffineTransform;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.apache.sis.coverage.grid.GridCoverage;
import org.apache.sis.coverage.grid.GridGeometry;
import org.opengis.referencing.datum.PixelInCell;

import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

/**
 * Returns the georeference X skew (or rotation parameter).
 */
public class SkewX extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
        CoverageWrapper wrapper=CoverageWrapper.extract(v);
		GridCoverage raster=wrapper.getXYGeometry();
		GridGeometry gridGeometry2D = raster.getGridGeometry();
        AffineTransform gridToWorld = (AffineTransform) gridGeometry2D.getGridToCRS(PixelInCell.CELL_CENTER);
        return NodeValue.makeDouble(gridToWorld.getShearX());
	}

}
