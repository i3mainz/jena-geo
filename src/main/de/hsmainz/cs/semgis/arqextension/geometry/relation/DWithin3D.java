package de.hsmainz.cs.semgis.arqextension.geometry.relation;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import org.apache.sis.coverage.grid.GridCoverage;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.operation.distance3d.Distance3DOp;
import org.opengis.geometry.Envelope;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import de.hsmainz.cs.semgis.arqextension.util.Wrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

public class DWithin3D extends FunctionBase3 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2, NodeValue v3) {
        Wrapper wrapper1=LiteralUtils.rasterOrVector(v1);
		Wrapper wrapper2=LiteralUtils.rasterOrVector(v2);
        Double withinDistance = v3.getDouble();
		if(wrapper1 instanceof GeometryWrapper && wrapper2 instanceof GeometryWrapper) {
			return NodeValue.makeBoolean(Distance3DOp.isWithinDistance(((GeometryWrapper)wrapper1).getXYGeometry(), ((GeometryWrapper)wrapper2).getXYGeometry(), withinDistance));
		}else if(wrapper1 instanceof CoverageWrapper && wrapper2 instanceof CoverageWrapper) {
			GridCoverage raster=((CoverageWrapper)wrapper1).getXYGeometry();
			GridCoverage raster2=((CoverageWrapper)wrapper2).getXYGeometry();	
	        Envelope bbox1 = raster.getGridGeometry().getEnvelope();
	        Envelope bbox2 = raster2.getGridGeometry().getEnvelope();
			return NodeValue.makeBoolean(Distance3DOp.isWithinDistance(LiteralUtils.toGeometry(bbox1), LiteralUtils.toGeometry(bbox2), withinDistance));		
		}else {
			if(wrapper1 instanceof CoverageWrapper) {
				GridCoverage raster=((CoverageWrapper)wrapper1).getXYGeometry();
				Envelope bbox1 = raster.getGridGeometry().getEnvelope();
				Geometry geom=((GeometryWrapper)wrapper2).getXYGeometry();
				return NodeValue.makeBoolean(Distance3DOp.isWithinDistance(LiteralUtils.toGeometry(bbox1), geom, withinDistance));
			}else {
				GridCoverage raster=((CoverageWrapper)wrapper2).getXYGeometry();
				Envelope bbox1 = raster.getGridGeometry().getEnvelope();
				Geometry geom=((GeometryWrapper)wrapper1).getXYGeometry();
				return NodeValue.makeBoolean(Distance3DOp.isWithinDistance(LiteralUtils.toGeometry(bbox1), geom, withinDistance));			
			}
		}
	}

}
