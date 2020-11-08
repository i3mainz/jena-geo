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

import org.locationtech.jts.geom.Coordinate;
import org.opengis.geometry.DirectPosition;
import org.opengis.referencing.datum.PixelInCell;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import org.apache.sis.coverage.grid.GridCoverage;
import org.apache.sis.coverage.grid.GridGeometry;
import org.apache.sis.geometry.DirectPosition2D;
/**
 * Returns the column in the raster of the point geometry (pt) or a X and Y world coordinate (xw, yw) represented in world spatial reference system of raster.
 *
 */
public class WorldToRasterCoordX extends FunctionBase3{

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2, NodeValue v3) {
		Integer longitude = v2.getInteger().intValue();
        Integer latitude = v3.getInteger().intValue();
        try {
        	CoverageWrapper wrapper=CoverageWrapper.extract(v1);
        	GridCoverage raster=wrapper.getXYGeometry();
        	
        	 GridGeometry gg2D = raster.getGridGeometry();
             MathTransform gridToCRS = gg2D.getGridToCRS(PixelInCell.CELL_CENTER);
             MathTransform crsToGrid = gridToCRS.inverse();
             DirectPosition realPos=new DirectPosition2D(latitude, longitude);
             DirectPosition gridPos = new DirectPosition2D();
             DirectPosition res=crsToGrid.transform(realPos, gridPos);
             Coordinate coord=new Coordinate(res.getCoordinate()[0],res.getCoordinate()[1]);
             return NodeValue.makeDouble(coord.x);
        } catch (TransformException e) {
            return NodeValue.nvNothing;
        }
	}

}
