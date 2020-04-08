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

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.apache.sis.coverage.grid.GridCoverage;
import org.locationtech.jts.geom.Geometry;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;

public class Envelope extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
		CoverageWrapper wrapper=CoverageWrapper.extract(v);
		GridCoverage raster=wrapper.getParsingGeometry();
	       Geometry envelope = LiteralUtils.toGeometry(raster.getGridGeometry().getEnvelope());
	        GeometryWrapper envelopeWrapper = GeometryWrapperFactory.createGeometry(envelope, wrapper.getSrsURI(), wrapper.getRasterDatatypeURI());
	        return envelopeWrapper.asNodeValue();
	}

}
