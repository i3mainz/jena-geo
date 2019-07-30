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
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import java.util.List;
import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import org.apache.jena.sparql.function.FunctionEnv;
import org.apache.jena.vocabulary.XSD;
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.locationtech.jts.geom.CoordinateXY;
import org.opengis.coverage.grid.GridCoordinates;
import org.opengis.coverage.grid.GridCoverage;
import org.opengis.referencing.operation.TransformException;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import de.hsmainz.cs.semgis.arqextension.util.Wrapper;


/**
 * Returns the upper left corner as column and row given geometric X and Y (longitude and latitude) or a point geometry expressed in the spatial reference coordinate system of the raster.
 *
 */
public class WorldToRasterCoord extends FunctionBase3 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2,NodeValue v3) {
        Integer longitude = v2.getInteger().intValue();
        Integer latitude = v3.getInteger().intValue();

        try {
        	CoverageWrapper wrapper=CoverageWrapper.extract(v1);
        	GridCoverage2D raster=wrapper.getXYGeometry();
            GridCoordinates position = raster.getGridGeometry().getworldToGrid(new org.apache.sis.geometry.DirectPosition2D(longitude, latitude));
            CoordinateXY coord = new CoordinateXY(position.getX(), position.getY());
            GeometryWrapper pointWrapper = GeometryWrapperFactory.createPoint(coord, geometryWrapper.getSrsURI(), geometryWrapper.getGeometryDatatypeURI());
            return pointWrapper.asNodeValue();

        } catch (TransformException e) {
            return NodeValue.nvNothing;
        }
	}

}
