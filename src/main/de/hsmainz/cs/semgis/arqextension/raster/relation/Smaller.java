package de.hsmainz.cs.semgis.arqextension.raster.relation;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.apache.sis.coverage.grid.GridCoverage;
import org.locationtech.jts.geom.Geometry;
import org.opengis.geometry.Envelope;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import de.hsmainz.cs.semgis.arqextension.util.Wrapper;
import de.hsmainz.cs.semgis.arqextension.vocabulary.WKT;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

public class Smaller extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		Wrapper wrapper1=LiteralUtils.rasterOrVector(v1);
		Wrapper wrapper2=LiteralUtils.rasterOrVector(v2);
		if(wrapper1 instanceof CoverageWrapper && wrapper2 instanceof CoverageWrapper) {
			GridCoverage raster=((CoverageWrapper)wrapper1).getGridGeometry();
			GridCoverage raster2=((CoverageWrapper)wrapper2).getGridGeometry();		
	        Envelope bbox1 = raster.getGridGeometry().getEnvelope();
	        Envelope bbox2 = raster2.getGridGeometry().getEnvelope();
	        return GeometryWrapperFactory.createGeometry(LiteralUtils.toGeometry(bbox1).symDifference(LiteralUtils.toGeometry(bbox2)),WKT.DATATYPE_URI).asNodeValue();
		}else {
			if(wrapper1 instanceof CoverageWrapper) {
				GridCoverage raster=((CoverageWrapper)wrapper1).getGridGeometry();
				Envelope bbox1 = raster.getGridGeometry().getEnvelope();
				Geometry geom=((GeometryWrapper)wrapper2).getXYGeometry();
				return GeometryWrapperFactory.createGeometry(LiteralUtils.toGeometry(bbox1).symDifference(geom),WKT.DATATYPE_URI).asNodeValue();
				//return GeometryWrapperFactory.createGeometry(geom.symDifference(raster.get).getParsingGeometry(), ((GeometryWrapper)wrapper1).getSrsURI(), ((GeometryWrapper)wrapper1).getGeometryDatatypeURI()).asNodeValue();

				//return NodeValue.makeBoolean(LiteralUtils.toGeometry(bbox1.getBounds()).coveredBy(geom));
			}else {
				GridCoverage raster=((CoverageWrapper)wrapper2).getGridGeometry();
				Envelope bbox1 = raster.getGridGeometry().getEnvelope();
				Geometry geom=((GeometryWrapper)wrapper1).getXYGeometry();
				return GeometryWrapperFactory.createGeometry(LiteralUtils.toGeometry(bbox1).symDifference(geom),WKT.DATATYPE_URI).asNodeValue();
				//return NodeValue.makeBoolean(geom.coveredBy(LiteralUtils.toGeometry(bbox1.getBounds())));				
			}
		}
		
	}

}
