package de.hsmainz.cs.semgis.arqextension.raster.algebra;

import java.awt.image.renderable.ParameterBlock;
import java.util.LinkedList;
import java.util.List;

import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.apache.sis.coverage.Category;
import org.apache.sis.coverage.SampleDimension;
import org.apache.sis.coverage.grid.GridCoverage;
import org.apache.sis.coverage.grid.GridExtent;
import org.apache.sis.coverage.grid.GridGeometry;
import org.apache.sis.internal.coverage.BufferedGridCoverage;
import org.apache.sis.util.iso.DefaultNameFactory;
import org.opengis.referencing.datum.PixelInCell;

import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

public class MinFilter extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		CoverageWrapper wrapper=CoverageWrapper.extract(v1);
		GridCoverage raster=wrapper.getXYGeometry();
		CoverageWrapper wrapper2=CoverageWrapper.extract(v2);
		GridCoverage raster2=wrapper2.getXYGeometry();
		 ParameterBlock pbSubtracted = new ParameterBlock(); 
	     pbSubtracted.addSource(raster.render(null)); 
	     pbSubtracted.addSource(raster2.render(null)); 
	     RenderedOp subtractedImage = JAI.create("minfilter",pbSubtracted);
	 	final SampleDimension sd =raster.getSampleDimensions().get(0);
		List<SampleDimension>sds=new LinkedList<SampleDimension>();
		sds.add(sd);
        GridExtent extent=new GridExtent(subtractedImage.getWidth(), subtractedImage.getHeight());
        GridGeometry gridgeom=new GridGeometry(extent, PixelInCell.CELL_CENTER, raster.getGridGeometry().getGridToCRS(PixelInCell.CELL_CENTER), raster.getCoordinateReferenceSystem());
        List<SampleDimension> dimensions=new LinkedList<SampleDimension>();
        DefaultNameFactory fac=new DefaultNameFactory();
        for(int i=0;i<subtractedImage.getNumBands();i++) {
        	dimensions.add(new SampleDimension(fac.createGenericName(null,  "Dimension "+i),0.,new LinkedList<Category>()));
        }
        BufferedGridCoverage coverage=new BufferedGridCoverage(
        		gridgeom, dimensions, subtractedImage.getData().getDataBuffer());
			return CoverageWrapper.createCoverage((GridCoverage)coverage, wrapper.getSrsURI(), wrapper.getRasterDatatypeURI())
					.asNodeValue();
	     
	} 

}
