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

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

import java.awt.image.DataBuffer;
import java.awt.image.WritableRaster;
import java.util.List;

import javax.media.jai.RasterFactory;

import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase0;
import org.apache.jena.sparql.function.FunctionEnv;
import org.apache.jena.vocabulary.XSD;
import org.apache.sis.geometry.Envelope2D;
import org.apache.sis.referencing.CommonCRS;
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.geotoolkit.coverage.grid.GridCoverageBuilder;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

public class MakeEmptyRaster extends FunctionBase0 {

	@Override
	public NodeValue exec() {
		Integer width;
		Integer height;
		Double upperleftx;
		Double upperlefty;
		Double pixelsize;
        WritableRaster raster = RasterFactory.createBandedRaster(DataBuffer.TYPE_FLOAT,
                width, height, 1, null);
for (int y=0; y<height; y++) {
for (int x=0; x<width; x++) {
raster.setSample(x, y, 0, x+y);
}
}
        CoordinateReferenceSystem crs = CommonCRS.WGS84.normalizedGeographic();
        Envelope2D envelope = new Envelope2D(crs, 0, 0, 30, 30);

        GridCoverageBuilder gcb = new GridCoverageBuilder();
        gcb.setName("My grayscale coverage");
        gcb.setRenderedImage(raster);
        gcb.setEnvelope(envelope);
        GridCoverage2D gc = gcb.getGridCoverage2D();

		return null;
	}

}
