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
package de.hsmainz.cs.semgis.arqextension.raster.constructor;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.HexWKBRastDatatype;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.WKBRastDatatype;

import java.awt.image.DataBuffer;
import java.awt.image.WritableRaster;
import java.util.LinkedList;
import java.util.List;

import javax.media.jai.RasterFactory;

import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase0;
import org.apache.jena.sparql.function.FunctionEnv;
import org.apache.jena.vocabulary.XSD;
import org.apache.sis.coverage.Category;
import org.apache.sis.coverage.SampleDimension;
import org.apache.sis.coverage.grid.GridExtent;
import org.apache.sis.coverage.grid.GridGeometry;
import org.apache.sis.geometry.Envelope2D;
import org.apache.sis.internal.coverage.BufferedGridCoverage;
import org.apache.sis.referencing.CRS;
import org.apache.sis.referencing.CommonCRS;
import org.apache.sis.util.iso.DefaultNameFactory;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.datum.PixelInCell;
import org.opengis.util.FactoryException;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;

public class MakeEmptyRaster extends FunctionBase0 {

	@Override
	public NodeValue exec() {
		Integer width=10;
		Integer height=20;
		Double upperleftx;
		Double upperlefty;
		Double pixelsize;
		WritableRaster raster = RasterFactory.createBandedRaster(DataBuffer.TYPE_FLOAT, width, height, 1, null);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				raster.setSample(x, y, 0, x + y);
			}
		}
		//CoordinateReferenceSystem crs = CommonCRS.WGS84.defaultGeographic();
		CoordinateReferenceSystem crss=null;
		Envelope2D envelope=null;
		try {
			crss = CRS.forCode("EPSG:4326");
			envelope = new Envelope2D(crss, 0, 0, 30, 30);
		} catch (FactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			envelope = new Envelope2D(CommonCRS.WGS84.defaultGeographic(), 0, 0, 30, 30);
		}
		
        GridExtent extent=new GridExtent(raster.getWidth(), raster.getHeight());
        GridGeometry gridgeom=new GridGeometry(extent, PixelInCell.CELL_CENTER, null, crss);
        List<SampleDimension> dimensions=new LinkedList<SampleDimension>();
        DefaultNameFactory fac=new DefaultNameFactory();
        for(int i=0;i<raster.getNumBands();i++) {
        	dimensions.add(new SampleDimension(fac.createGenericName(null,  "Dimension "+i),0.,new LinkedList<Category>()));
        }
        BufferedGridCoverage coverage=new BufferedGridCoverage(
        		gridgeom, dimensions, raster.getDataBuffer());
		
		
		return CoverageWrapper.createCoverage(coverage, "EPSG:4326", HexWKBRastDatatype.URI.toString()).asNodeValue();
	}

	
	
}
