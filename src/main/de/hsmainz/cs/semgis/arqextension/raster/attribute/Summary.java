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

import java.awt.geom.AffineTransform;
import java.awt.image.RenderedImage;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.apache.sis.coverage.grid.GridCoverage;
import org.apache.sis.coverage.grid.GridGeometry;
import org.opengis.coverage.CannotEvaluateException;
import org.opengis.referencing.datum.PixelInCell;

public class Summary extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
		CoverageWrapper wrapper=CoverageWrapper.extract(v);
		GridCoverage raster=wrapper.getXYGeometry();
		StringBuilder builder = new StringBuilder();
		RenderedImage rendered;
				rendered = raster.render(null);
        builder.append("Raster of " + rendered.getWidth() + "x" + rendered.getHeight() +"\n MemSize: "+raster.render(null).getData().getDataBuffer().getSize()+ 
        		"\nMINX/Y: ["+rendered.getMinX()+","+rendered.getMinY()+"] pixels has " 
        		+ raster.getSampleDimensions().size() + " bands\n and extent of " +raster.getGridGeometry().getEnvelope().toString()
        		);//+"\n and grid geometry of "+ raster.getGridGeometry() + System.lineSeparator());
        builder.append("SampleModel: "+rendered.getSampleModel()+"\n");
        builder.append("PropertyNames: "+rendered.getPropertyNames()+"\n");
        builder.append("Tiles: "+rendered.getNumXTiles()+"/"+rendered.getNumYTiles()+"["+rendered.getTileWidth()+"/"+rendered.getTileHeight()+"] Offset: ["+rendered.getTileGridXOffset()+"/"+rendered.getTileGridYOffset()+"]\n");
        builder.append("Dimensions: "+raster.getGridGeometry().getDimension()+"\n");
        builder.append("DataElements: "+rendered.getData().getNumDataElements()+"\n");
        builder.append("DataType: "+rendered.getData().getDataBuffer().getDataType()+"\n");
        builder.append("ColorModel: "+rendered.getColorModel()+"\n");
		GridGeometry gridGeometry2D = raster.getGridGeometry();
        AffineTransform gridToWorld = (AffineTransform) gridGeometry2D.getGridToCRS(PixelInCell.CELL_CENTER);
        builder.append("Shear: ["+gridToWorld.getShearX()+"/"+gridToWorld.getShearY()+"] Scale: ["+gridToWorld.getScaleX()+"/"+gridToWorld.getScaleY()+"]\n");
        builder.append("GridToWorld: ["+gridToWorld. getShearX()+"/"+gridToWorld.getShearY()+"] Scale: ["+gridToWorld.getScaleX()+"/"+gridToWorld.getScaleY()+"]\n");
        builder.append("PixelData: \n");
        for(int k=0;k<raster.getSampleDimensions().size();k++) {
        	builder.append("Band "+k+"\n");
        	for(int i=0;i<rendered.getSampleModel().getWidth();i++) {
        		builder.append("| ");
        		for(int j=0;j<rendered.getSampleModel().getHeight();j++) {
        			builder.append(rendered.getData().getSample(i, j, k)+" | ");
        		}
        		builder.append("\n");
        	}
        }
        builder.append("CRS: "+raster.getGridGeometry().getCoordinateReferenceSystem().getName()+"\n");
        for (int i = 0; i < raster.getSampleDimensions().size(); i++) {
            builder.append("band " + i + " of pixtype " + raster.getSampleDimensions().get(i).getMeasurementRange() + " is in-db with NODATA value of " + raster.getSampleDimensions().get(i).getNoDataValues() + System.lineSeparator());
        }
        return NodeValue.makeString(builder.toString());
	}

}
