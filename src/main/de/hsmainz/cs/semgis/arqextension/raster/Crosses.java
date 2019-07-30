package de.hsmainz.cs.semgis.arqextension.raster;

import java.awt.geom.Rectangle2D;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.locationtech.jts.geom.Geometry;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import de.hsmainz.cs.semgis.arqextension.util.Wrapper;
import io.github.galbiston.geosparql_jena.implementation.CoverageWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class Crosses extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		Wrapper wrapper1=LiteralUtils.rasterOrVector(v1);
		Wrapper wrapper2=LiteralUtils.rasterOrVector(v2);
		if(wrapper1 instanceof GeometryWrapper && wrapper2 instanceof GeometryWrapper) {
			return NodeValue.makeBoolean(((GeometryWrapper)wrapper1).getXYGeometry().crosses(((GeometryWrapper)wrapper2).getXYGeometry()));
		}else if(wrapper1 instanceof CoverageWrapper && wrapper2 instanceof CoverageWrapper) {
			GridCoverage2D raster=((CoverageWrapper)wrapper1).getXYGeometry();
			GridCoverage2D raster2=((CoverageWrapper)wrapper2).getXYGeometry();		
	        Rectangle2D bbox1 = raster.getEnvelope2D().getBounds2D();
	        Rectangle2D bbox2 = raster2.getEnvelope2D().getBounds2D();
	        return NodeValue.makeBoolean(LiteralUtils.toGeometry(bbox1.getBounds()).crosses(LiteralUtils.toGeometry(bbox2.getBounds())));			
		}else {
			if(wrapper1 instanceof CoverageWrapper) {
				GridCoverage2D raster=((CoverageWrapper)wrapper1).getXYGeometry();
				Rectangle2D bbox1 = raster.getEnvelope2D().getBounds2D();
				Geometry geom=((GeometryWrapper)wrapper2).getXYGeometry();
				return NodeValue.makeBoolean(LiteralUtils.toGeometry(bbox1.getBounds()).crosses(geom));
			}else {
				GridCoverage2D raster=((CoverageWrapper)wrapper2).getXYGeometry();
				Rectangle2D bbox1 = raster.getEnvelope2D().getBounds2D();
				Geometry geom=((GeometryWrapper)wrapper1).getXYGeometry();
				return NodeValue.makeBoolean(geom.crosses(LiteralUtils.toGeometry(bbox1.getBounds())));				
			}
		}
	}

}
