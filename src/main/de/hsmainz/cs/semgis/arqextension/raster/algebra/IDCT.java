package de.hsmainz.cs.semgis.arqextension.raster.algebra;

import java.awt.image.renderable.ParameterBlock;

import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.geotoolkit.coverage.grid.GridCoverageBuilder;
import org.opengis.coverage.grid.GridCoverage;

import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

public class IDCT extends FunctionBase2  {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		CoverageWrapper wrapper=CoverageWrapper.extract(v1);
		GridCoverage2D raster=wrapper.getXYGeometry();
		 ParameterBlock pbSubtracted = new ParameterBlock(); 
	     pbSubtracted.addSource(raster.getRenderedImage()); 
	     RenderedOp subtractedImage = JAI.create("idct",pbSubtracted);
			GridCoverageBuilder builder=new GridCoverageBuilder();
			builder.setGridGeometry(raster.getGridGeometry());
			builder.setNumBands(raster.getNumSampleDimensions());
			builder.setExtent(raster.getGridGeometry().getExtent());
			builder.setRenderedImage(subtractedImage);
			/*final SampleDimension sd = new SampleDimension.Builder().setName("t")
					.addQuantitative(
							(raster.getSampleDimensions().get(rd1).getName() + "+"
									+ raster2.getSampleDimensions().get(rd2).getName()).toString(),
							raster.getSampleDimensions().get(0).getMeasurementRange().get(),
							raster.getSampleDimensions().get(0).getTransferFunction().get(),
							raster.getSampleDimensions().get(0).getUnits().get())
					.build();
			
			List<SampleDimension>sds=new LinkedList<SampleDimension>();
			sds.add(sd);
			/*
			 * Create the grid coverage, gets its image and set values directly as short
			 * integers.
			 
			BufferedGridCoverage coverage = new BufferedGridCoverage(raster2.getGridGeometry(),
					sds, DataBuffer.TYPE_SHORT);
			WritableRaster rasterr = ((BufferedImage) coverage.render(null)).getRaster();
			rasterr.setRect(subtractedImage.getSourceImage(0).getData());
			return CoverageWrapper.createCoverage(coverage, wrapper.getSrsURI(), wrapper.getRasterDatatypeURI())
					.asNodeValue();	
			} catch (CannotEvaluateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}*/
			GridCoverage cov=builder.build();
			return CoverageWrapper.createCoverage((GridCoverage2D)cov, wrapper.getSrsURI(), wrapper.getRasterDatatypeURI())
					.asNodeValue();
	}
}
