package de.hsmainz.cs.semgis.arqextension.raster.algebra;

import java.awt.image.renderable.ParameterBlock;
import java.math.BigInteger;

import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.apache.sis.coverage.grid.GridCoverage;
import org.opengis.coverage.Coverage;

import io.github.galbiston.geosparql_jena.implementation.CoverageWrapper;

public class Add extends FunctionBase2  {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		CoverageWrapper wrapper=CoverageWrapper.extract(v1);
		GridCoverage raster=wrapper.getXYGeometry();
		CoverageWrapper wrapper2=CoverageWrapper.extract(v2);
		GridCoverage raster2=wrapper.getXYGeometry();
		 ParameterBlock pbSubtracted = new ParameterBlock(); 
	     pbSubtracted.addSource(raster.render(raster.getGridGeometry().getExtent())); 
	     pbSubtracted.addSource(raster2.render(raster2.getGridGeometry().getExtent())); 
	     RenderedOp subtractedImage = JAI.create("add",pbSubtracted);
	     GridCoverageFactory gridCoverageFactory = new GridCoverageFactory(); 
	     Envelope referencedEnvelope = new ReferencedEnvelope(REDC.getEnvelope()); 
	        Coverage coverage = gridCoverageFactory.create("Raster", NDVIop, REDC.getEnvelope()); 
	     return CoverageWrapper.createGeometry(subtractedImage.getAsBufferedImage(), wrapper.getSrsURI(), wrapper.getRasterDatatypeURI()).asNodeValue();

	     
	} 
	
}
