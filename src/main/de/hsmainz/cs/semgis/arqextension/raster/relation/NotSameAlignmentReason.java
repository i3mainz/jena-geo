package de.hsmainz.cs.semgis.arqextension.raster.relation;

import java.awt.image.RenderedImage;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.apache.sis.coverage.grid.CannotEvaluateException;
import org.apache.sis.coverage.grid.GridCoverage;

import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

public class NotSameAlignmentReason extends FunctionBase2 {


	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
        CoverageWrapper wrapper=CoverageWrapper.extract(v1);
		GridCoverage raster=wrapper.getGridGeometry();
        CoverageWrapper wrapper2=CoverageWrapper.extract(v2);
		GridCoverage raster2=wrapper2.getGridGeometry();
		RenderedImage image1;
		try {
			image1 = raster.render(raster.getGridGeometry().getExtent());

		RenderedImage image2 = raster2.render(raster2.getGridGeometry().getExtent());
        Integer raster1_offset = image1.getData().getDataBuffer().getOffset();
        Integer raster2_offset = image2.getData().getDataBuffer().getOffset();
        if(raster1_offset!=raster2_offset) {
        	return NodeValue.makeString("Offset is different!");
        }
        if(!raster.getGridGeometry().getCoordinateReferenceSystem().
                equals(raster2.getGridGeometry().getCoordinateReferenceSystem())) {
        	return NodeValue.makeString("Raster SRIDs are different!");
        }
        if(raster.getGridGeometry().getEnvelope().getUpperCorner().
                equals(raster.getGridGeometry().getEnvelope().getUpperCorner())
              || raster.getGridGeometry().getEnvelope().getLowerCorner().
              equals(raster.getGridGeometry().getEnvelope().getLowerCorner())) {
        	return NodeValue.makeString("Corner points are different!");
        }
		} catch (CannotEvaluateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return NodeValue.makeString("Rasters have same alignment!");
	}

}
