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
package de.hsmainz.cs.semgis.arqextension.raster.relation;

import io.github.galbiston.geosparql_jena.implementation.CoverageWrapper;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.apache.sis.coverage.grid.GridCoverage;

/**
 * Returns true if rasters have same skew, scale, spatial ref, and offset (pixels can be put on same grid without cutting into pixels) and false if they don't with notice detailing issue.
 *
 */
public class SameAlignment extends FunctionBase2 {


	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
        CoverageWrapper wrapper=CoverageWrapper.extract(v1);
		GridCoverage raster=wrapper.getXYGeometry();
        CoverageWrapper wrapper2=CoverageWrapper.extract(v2);
		GridCoverage raster2=wrapper2.getXYGeometry();
        Integer raster1_offset = raster.render(raster.getGridGeometry().getExtent()).getData().getDataBuffer().getOffset();
        Integer raster2_offset = raster.render(raster.getGridGeometry().getExtent()).getData().getDataBuffer().getOffset();
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
