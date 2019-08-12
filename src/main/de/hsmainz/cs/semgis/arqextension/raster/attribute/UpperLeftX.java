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

import io.github.galbiston.geosparql_jena.implementation.CoverageWrapper;

import java.awt.geom.Point2D;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.apache.sis.coverage.grid.GridCoverage;
import org.apache.sis.coverage.grid.IllegalGridGeometryException;
import org.opengis.referencing.operation.TransformException;


/**
 * Returns the upper left X coordinate of raster in projected spatial ref.
 *
 */
public class UpperLeftX extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
		CoverageWrapper wrapper=CoverageWrapper.extract(v);
		
		GridCoverage raster=wrapper.getXYGeometry();	
		Point2D position;
		try {
			position = raster.getGridGeometry().getGridToCRS2D().transform(new GridCoordinates(0, 0),null);
			return NodeValue.makeDouble(position.getX());
		} catch (IllegalGridGeometryException | TransformException e) {
			throw new AssertionError("InvalidGeometryException");
		}
        
	}

}
