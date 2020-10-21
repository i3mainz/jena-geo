package de.hsmainz.cs.semgis.arqextension.geometry.relation;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.locationtech.jts.geom.Geometry;
import org.opengis.geometry.Envelope;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import de.hsmainz.cs.semgis.arqextension.util.Wrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

public class EqualSRS extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		Wrapper wrapper1=LiteralUtils.rasterOrVector(v1);
		Wrapper wrapper2=LiteralUtils.rasterOrVector(v2);
		if(wrapper1 instanceof GeometryWrapper && wrapper2 instanceof GeometryWrapper) {
			return NodeValue.makeBoolean(((GeometryWrapper)wrapper1).getXYGeometry().getSRID()==(((GeometryWrapper)wrapper2).getXYGeometry().getSRID()));
		}else if(wrapper1 instanceof CoverageWrapper && wrapper2 instanceof CoverageWrapper) {
			GridCoverage2D raster=((CoverageWrapper)wrapper1).getXYGeometry();
			GridCoverage2D raster2=((CoverageWrapper)wrapper2).getXYGeometry();
	        Envelope bbox1 = raster.getGridGeometry().getEnvelope();
	        Envelope bbox2 = raster2.getGridGeometry().getEnvelope();
	        return NodeValue.makeBoolean(LiteralUtils.toGeometry(bbox1).getSRID()==LiteralUtils.toGeometry(bbox2).getSRID());			
		}else {
			if(wrapper1 instanceof CoverageWrapper) {
				GridCoverage2D raster=((CoverageWrapper)wrapper1).getXYGeometry();
				Envelope bbox1 = raster.getGridGeometry().getEnvelope();
				Geometry geom=((GeometryWrapper)wrapper2).getXYGeometry();
				return NodeValue.makeBoolean(LiteralUtils.toGeometry(bbox1).getSRID()==geom.getSRID());
			}else {
				GridCoverage2D raster=((CoverageWrapper)wrapper2).getXYGeometry();
				Envelope bbox1 = raster.getGridGeometry().getEnvelope();
				Geometry geom=((GeometryWrapper)wrapper1).getXYGeometry();
				return NodeValue.makeBoolean(geom.getSRID()==(LiteralUtils.toGeometry(bbox1)).getSRID());				
			}
		}
	}

}
