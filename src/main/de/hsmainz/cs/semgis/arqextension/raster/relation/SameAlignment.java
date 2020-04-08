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

import java.awt.image.RenderedImage;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.apache.sis.coverage.grid.GridCoverage;

import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

/**
 * Returns true if rasters have same skew, scale, spatial ref, and offset (pixels can be put on same grid without cutting into pixels) and false if they don't with notice detailing issue.
 *
 */
public class SameAlignment extends FunctionBase2 {


	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
        CoverageWrapper wrapper=CoverageWrapper.extract(v1);
		GridCoverage raster=wrapper.getGridGeometry();
        CoverageWrapper wrapper2=CoverageWrapper.extract(v2);
		GridCoverage raster2=wrapper2.getGridGeometry();
		RenderedImage image1 = raster.render(raster.getGridGeometry().getExtent());
		RenderedImage image2 = raster2.render(raster2.getGridGeometry().getExtent());
        Integer raster1_offset = image1.getData().getDataBuffer().getOffset();
        Integer raster2_offset = image2.getData().getDataBuffer().getOffset();
        if(raster1_offset==raster2_offset && 
          raster.getGridGeometry().getCoordinateReferenceSystem().
          equals(raster.getGridGeometry().getCoordinateReferenceSystem())
          && (
             raster.getGridGeometry().getEnvelope().getUpperCorner().
             equals(raster.getGridGeometry().getEnvelope().getUpperCorner())
           || raster.getGridGeometry().getEnvelope().getLowerCorner().
           equals(raster.getGridGeometry().getEnvelope().getLowerCorner())
        		  )) {
        	return NodeValue.TRUE;
        }
        return NodeValue.FALSE;
	}

}
