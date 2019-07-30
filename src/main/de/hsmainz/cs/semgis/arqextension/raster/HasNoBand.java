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

/**
 * Returns true if there is no band with given band number. If no band number is specified, then band number 1 is assumed.
 *
 */
public class HasNoBand extends FunctionBase2 {

	
	@Override
	public NodeValue exec(NodeValue v, NodeValue v1) {
		CoverageWrapper wrapper=CoverageWrapper.extract(v);
		GridCoverage2D raster=wrapper.getXYGeometry();
		BigInteger noband=v1.getInteger();
		if(noband.intValue()>raster.getRenderedImage().getData().getNumBands()) {
			return NodeValue.FALSE;
		}
		return NodeValue.TRUE;
	}

}
