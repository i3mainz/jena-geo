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

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase4;
import org.apache.sis.coverage.grid.GridCoverage;

import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

/**
 * Returns the nearest non-NODATA value of a given band's pixel specified by a columnx and rowy or a geometric point expressed in the same spatial reference coordinate system as the raster. 
 *
 */
public class NearestValue extends FunctionBase4 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2, NodeValue v3, NodeValue v4) {
		CoverageWrapper wrapper=CoverageWrapper.extract(v1);
		GridCoverage raster=wrapper.getXYGeometry();	
        Integer bandnum = v2.getInteger().intValue();
        Integer column = v3.getInteger().intValue();
        Integer row = v4.getInteger().intValue();
        Double d = ((double[]) raster.render(raster.getGridGeometry().getExtent()).getData().getDataElements(column, row, new double[]{}))[0];
        return NodeValue.makeDouble(d);
	}

}
