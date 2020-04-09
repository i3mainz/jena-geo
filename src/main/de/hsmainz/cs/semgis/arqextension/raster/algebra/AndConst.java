package de.hsmainz.cs.semgis.arqextension.raster.algebra;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.WritableRaster;
import java.awt.image.renderable.ParameterBlock;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import org.apache.sis.coverage.SampleDimension;
import org.apache.sis.coverage.grid.GridCoverage;
import org.apache.sis.internal.coverage.BufferedGridCoverage;

import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

public class AndConst extends FunctionBase3  {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2, NodeValue v3) {
		CoverageWrapper wrapper=CoverageWrapper.extract(v1);
		GridCoverage raster=wrapper.getXYGeometry();
		BigInteger bandnum=v2.getInteger();
		Double constval=v3.getDouble();
		double[] consts=new double[raster.getSampleDimensions().size()];
		if(bandnum.intValue()<0) {
			for(int i=0;i<consts.length;i++) {
				consts[i]=constval;
			}
		}else {
			for(int i=0;i<consts.length;i++) {
				if(i==bandnum.intValue()) {
					consts[i]=constval;
				}else {
					consts[i]=1;
				}
			}
		}
		 ParameterBlock pbSubtracted = new ParameterBlock(); 
	     pbSubtracted.addSource(raster.render(raster.getGridGeometry().getExtent())); 
	     pbSubtracted.add(consts);
	     RenderedOp subtractedImage = JAI.create("andconst",pbSubtracted);
			/*
			 * final GridGeometry grid = new
			 * GridGeometry(raster.getGridGeometry().getExtent(), PixelInCell.CELL_CENTER,
			 * MathTransforms.identity(2),
			 * raster.getGridGeometry().getCoordinateReferenceSystem());
			 * 
			 * final MathTransform1D toUnits = (MathTransform1D) MathTransforms.linear(0.5,
			 * 100);
			 */
			final SampleDimension sd = new SampleDimension.Builder().setName("t")
					.addQuantitative(
							(raster.getSampleDimensions().get(bandnum.intValue()).getName() + " andconst "+constval),
							raster.getSampleDimensions().get(bandnum.intValue()).getMeasurementRange().get(),
							raster.getSampleDimensions().get(bandnum.intValue()).getTransferFunction().get(),
							raster.getSampleDimensions().get(bandnum.intValue()).getUnits().get())
					.build();
			
			List<SampleDimension>sds=new LinkedList<SampleDimension>();
			sds.add(sd);
			/*
			 * Create the grid coverage, gets its image and set values directly as short
			 * integers.
			 */
			BufferedGridCoverage coverage = new BufferedGridCoverage(raster.getGridGeometry(),
					sds, DataBuffer.TYPE_SHORT);
			WritableRaster rasterr = ((BufferedImage) coverage.render(null)).getRaster();
			rasterr.setRect(subtractedImage.getSourceImage(0).getData());
			return CoverageWrapper.createGeometry(coverage, wrapper.getSrsURI(), wrapper.getRasterDatatypeURI())
					.asNodeValue();	
	     
	} 

}
