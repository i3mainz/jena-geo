package de.hsmainz.cs.semgis.arqextension.geometry.relation;

import java.awt.geom.Rectangle2D;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.operation.distance3d.Distance3DOp;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import de.hsmainz.cs.semgis.arqextension.util.Wrapper;
import io.github.galbiston.geosparql_jena.implementation.CoverageWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class DWithin3D extends FunctionBase3 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2, NodeValue v3) {
        Wrapper wrapper1=LiteralUtils.rasterOrVector(v1);
		Wrapper wrapper2=LiteralUtils.rasterOrVector(v2);
        Double withinDistance = v3.getDouble();
		if(wrapper1 instanceof GeometryWrapper && wrapper2 instanceof GeometryWrapper) {
			return NodeValue.makeBoolean(Distance3DOp.isWithinDistance(((GeometryWrapper)wrapper1).getXYGeometry(), ((GeometryWrapper)wrapper2).getXYGeometry(), withinDistance));
		}else if(wrapper1 instanceof CoverageWrapper && wrapper2 instanceof CoverageWrapper) {
			GridCoverage2D raster=((CoverageWrapper)wrapper1).getXYGeometry();
			GridCoverage2D raster2=((CoverageWrapper)wrapper2).getXYGeometry();	
	        Rectangle2D bbox1 = raster.getEnvelope2D().getBounds2D();
	        Rectangle2D bbox2 = raster2.getEnvelope2D().getBounds2D();
			return NodeValue.makeBoolean(Distance3DOp.isWithinDistance(LiteralUtils.toGeometry(bbox1.getBounds()), LiteralUtils.toGeometry(bbox2.getBounds()), withinDistance));		
		}else {
			if(wrapper1 instanceof CoverageWrapper) {
				GridCoverage2D raster=((CoverageWrapper)wrapper1).getXYGeometry();
				Rectangle2D bbox1 = raster.getEnvelope2D().getBounds2D();
				Geometry geom=((GeometryWrapper)wrapper2).getXYGeometry();
				return NodeValue.makeBoolean(Distance3DOp.isWithinDistance(LiteralUtils.toGeometry(bbox1.getBounds()), geom, withinDistance));
			}else {
				GridCoverage2D raster=((CoverageWrapper)wrapper2).getXYGeometry();
				Rectangle2D bbox1 = raster.getEnvelope2D().getBounds2D();
				Geometry geom=((GeometryWrapper)wrapper1).getXYGeometry();
				return NodeValue.makeBoolean(Distance3DOp.isWithinDistance(LiteralUtils.toGeometry(bbox1.getBounds()), geom, withinDistance));			
			}
		}
	}

}
