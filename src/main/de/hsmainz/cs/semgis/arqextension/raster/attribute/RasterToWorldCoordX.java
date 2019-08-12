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

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import org.apache.sis.coverage.grid.GridCoverage;
import org.apache.sis.coverage.grid.GridGeometry;
import org.apache.sis.geometry.DirectPosition2D;
import org.locationtech.jts.geom.Coordinate;
import org.opengis.geometry.DirectPosition;
import org.opengis.referencing.datum.PixelInCell;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

/**
 * Returns the geometric X coordinate upper left of a raster, column and row. Numbering of columns and rows starts at 1.
 *
 */
public class RasterToWorldCoordX extends FunctionBase3 {

	
	@Override
	public NodeValue exec(NodeValue v,NodeValue v1,NodeValue v2) {
		CoverageWrapper wrapper=CoverageWrapper.extract(v);
		GridCoverage raster=wrapper.getXYGeometry();	
		Integer column = v1.getInteger().intValue();
        Integer row = v2.getInteger().intValue();
        try {
        	
        	 GridGeometry gg2D = raster.getGridGeometry();
             MathTransform gridToCRS = gg2D.getGridToCRS(PixelInCell.CELL_CENTER);
             DirectPosition realPos=new DirectPosition2D(column, row);
             DirectPosition gridPos = new DirectPosition2D();
             DirectPosition res=gridToCRS.transform(realPos, gridPos);
             Coordinate coord=new Coordinate(res.getCoordinate()[0],res.getCoordinate()[1]);
             return NodeValue.makeDouble(coord.x);
        } catch (TransformException e) {
            return NodeValue.nvNothing;
        }
	}

}
