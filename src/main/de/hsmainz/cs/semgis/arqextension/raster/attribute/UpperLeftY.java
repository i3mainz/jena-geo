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

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.apache.sis.geometry.GeneralDirectPosition;
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.opengis.geometry.DirectPosition;
import org.opengis.referencing.datum.PixelInCell;
import org.opengis.referencing.operation.TransformException;

import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

/**
 * Returns the upper left Y coordinate of raster in projected spatial ref.
 *
 */
public class UpperLeftY extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
		CoverageWrapper wrapper=CoverageWrapper.extract(v);
		GridCoverage2D raster=wrapper.getXYGeometry();	
		double[] coords=new double[] {0,0};
		GeneralDirectPosition pos = new GeneralDirectPosition(coords);
		DirectPosition position;
		try {
			position = raster.getGridGeometry().getGridToCRS(PixelInCell.CELL_CENTER).transform(pos,null);
			return NodeValue.makeDouble(position.getCoordinate()[1]);
		} catch ( TransformException e) {
			throw new AssertionError("InvalidGeometryException");
		}
        
	}

}
