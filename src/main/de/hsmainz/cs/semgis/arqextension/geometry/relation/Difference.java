package de.hsmainz.cs.semgis.arqextension.geometry.relation;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.apache.sis.coverage.grid.GridCoverage;
import org.locationtech.jts.geom.Geometry;
import org.opengis.geometry.Envelope;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import de.hsmainz.cs.semgis.arqextension.util.Wrapper;
import io.github.galbiston.geosparql_jena.implementation.CoverageWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

public class Difference extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		Wrapper wrapper1=LiteralUtils.rasterOrVector(v1);
		Wrapper wrapper2=LiteralUtils.rasterOrVector(v2);
		if(wrapper1 instanceof GeometryWrapper && wrapper2 instanceof GeometryWrapper) {
			GeometryWrapper transGeom2;
			try {
				transGeom2 = ((GeometryWrapper)wrapper2).transform(((GeometryWrapper)wrapper1).getSrsInfo());
				return GeometryWrapperFactory.createGeometry(((GeometryWrapper)wrapper1).difference(transGeom2).getParsingGeometry(), ((GeometryWrapper)wrapper1).getSrsURI(), ((GeometryWrapper)wrapper1).getGeometryDatatypeURI()).asNodeValue();

			} catch (MismatchedDimensionException | TransformException | FactoryException e) {
				// TODO Auto-generated catch block
				return NodeValue.makeString(e.getMessage());
			}
		}else if(wrapper1 instanceof CoverageWrapper && wrapper2 instanceof CoverageWrapper) {
			GridCoverage raster=((CoverageWrapper)wrapper1).getXYGeometry();
			GridCoverage raster2=((CoverageWrapper)wrapper2).getXYGeometry();		
	        Envelope bbox1 = raster.getGridGeometry().getEnvelope();
	        Envelope bbox2 = raster2.getGridGeometry().getEnvelope();
	        return GeometryWrapperFactory.createGeometry(LiteralUtils.toGeometry(bbox1).difference(LiteralUtils.toGeometry(bbox2)),((GeometryWrapper)wrapper1).getGeometryDatatypeURI()).asNodeValue();			
		}else {
			if(wrapper1 instanceof CoverageWrapper) {
				GridCoverage raster=((CoverageWrapper)wrapper1).getXYGeometry();
				Envelope bbox1 = raster.getGridGeometry().getEnvelope();
				Geometry geom=((GeometryWrapper)wrapper2).getXYGeometry();
				return GeometryWrapperFactory.createGeometry(LiteralUtils.toGeometry(bbox1).difference(geom),((CoverageWrapper) wrapper1).getSrsURI()).asNodeValue();
			}else {
				GridCoverage raster=((CoverageWrapper)wrapper2).getXYGeometry();
				Envelope bbox1 = raster.getGridGeometry().getEnvelope();
				Geometry geom=((GeometryWrapper)wrapper1).getXYGeometry();
				return GeometryWrapperFactory.createGeometry(geom.difference(LiteralUtils.toGeometry(bbox1)),((CoverageWrapper) wrapper1).getSrsURI()).asNodeValue();				
			}
		}
	}

}
