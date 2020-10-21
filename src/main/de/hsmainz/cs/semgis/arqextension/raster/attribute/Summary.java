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


import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;


import java.awt.image.RenderedImage;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.opengis.coverage.CannotEvaluateException;

public class Summary extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
		CoverageWrapper wrapper=CoverageWrapper.extract(v);
		GridCoverage2D raster=wrapper.getXYGeometry();
		StringBuilder builder = new StringBuilder();
		RenderedImage rendered;
		try {
			rendered = raster.getRenderedImage();
        builder.append("Raster of " + rendered.getWidth() + "x" + rendered.getHeight() + " pixels has " + raster.getSampleDimensions() + " bands and extent of " + raster.getGridGeometry().toString() + System.lineSeparator());
        for (int i = 0; i < raster.getNumSampleDimensions(); i++) {
            builder.append("band " + i + " of pixtype " + raster.getSampleDimension(i).getCategories() + " is in-db with NODATA value of " + raster.getSampleDimension(i).getNoDataValues() + System.lineSeparator());
        }
        return NodeValue.makeString(builder.toString());
		} catch (CannotEvaluateException e) {
			return null;
		}
	}

}
