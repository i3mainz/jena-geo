package de.hsmainz.cs.semgis.arqextension.raster.relation;

import java.awt.image.RenderedImage;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.geotoolkit.coverage.grid.GridCoverage2D;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import de.hsmainz.cs.semgis.arqextension.util.Wrapper;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

public class RasterEquals extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		Wrapper wrapper1=LiteralUtils.rasterOrVector(v1);
		Wrapper wrapper2=LiteralUtils.rasterOrVector(v2);
		if(wrapper1 instanceof CoverageWrapper && wrapper2 instanceof CoverageWrapper) {
			GridCoverage2D raster=((CoverageWrapper)wrapper1).getGridGeometry();
			GridCoverage2D raster2=((CoverageWrapper)wrapper2).getGridGeometry();	
			RenderedImage rendered = raster.getRenderedImage();
			RenderedImage rendered2 = raster2.getRenderedImage();
			if(!raster.getEnvelope2D().equals(raster2.getEnvelope2D())) {
				return NodeValue.FALSE;
			}else {
				if(rendered.getWidth()!=rendered2.getWidth() || rendered.getHeight()!=rendered2.getHeight()) {
					return NodeValue.FALSE;
				}
		        for(int i=0;i<rendered.getSampleModel().getWidth();i++) {
		        	for(int j=0;j<rendered.getSampleModel().getHeight();j++) {
		        		if(rendered.getData().getSample(i, j, 0)!=rendered2.getData().getSample(i, j, 0)) {
		        			return NodeValue.FALSE;
		        		}
		        	}
		        }
			}
		
		}
		return NodeValue.FALSE;
	}

}
