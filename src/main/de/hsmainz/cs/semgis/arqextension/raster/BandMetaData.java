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

import io.github.galbiston.geosparql_jena.implementation.CoverageWrapper;

import java.math.BigInteger;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;

import org.geotoolkit.coverage.grid.GridCoverage2D;

public class BandMetaData extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		CoverageWrapper wrapper=CoverageWrapper.extract(v1);
		GridCoverage2D raster=wrapper.getXYGeometry();
		BigInteger bandNum=v2.getInteger();
        if (bandNum.intValue() > raster.getNumSampleDimensions()) {
            return NodeValue.nvNothing;
        }
        StringBuilder builder = new StringBuilder();
        builder.append("rid \t pixeltype \t nodatavalue \t isoutdb \t path" + System.lineSeparator());
        builder.append(bandNum + "\t" + raster.getSampleDimension(bandNum.intValue()).getColorModel().getTransferType()
                + "\t" + raster.getSampleDimension(bandNum.intValue()).getNoDataValues() + "\t\t\t" + System.lineSeparator());
        return NodeValue.makeString(builder.toString());
	}

}
