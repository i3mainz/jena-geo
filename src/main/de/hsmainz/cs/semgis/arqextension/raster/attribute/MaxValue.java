package de.hsmainz.cs.semgis.arqextension.raster.attribute;

import java.awt.image.RenderedImage;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.apache.sis.coverage.grid.GridCoverage;

import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

public class MaxValue extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		CoverageWrapper wrapper=CoverageWrapper.extract(v1);
		GridCoverage raster=wrapper.getXYGeometry();
		RenderedImage rendered=raster.render(null);
		Integer bandnum = v2.getInteger().intValue();
		Double maxVal=Double.MIN_VALUE;
        	for(int i=0;i<rendered.getSampleModel().getWidth();i++) {
        		for(int j=0;j<rendered.getSampleModel().getHeight();j++) {
        			if(rendered.getData().getSample(i, j, bandnum)>maxVal) {
        				maxVal=(double) rendered.getData().getSample(i, j, bandnum);
        			}
        		}
        	}
		return NodeValue.makeDouble(maxVal);
	}


}
