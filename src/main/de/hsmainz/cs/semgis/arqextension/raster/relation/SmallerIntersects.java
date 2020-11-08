package de.hsmainz.cs.semgis.arqextension.raster.relation;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase4;
import org.apache.sis.coverage.grid.GridCoverage;
import org.locationtech.jts.geom.Geometry;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.operation.TransformException;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import de.hsmainz.cs.semgis.arqextension.util.Wrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

public class SmallerIntersects extends FunctionBase4 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2, NodeValue v3, NodeValue v4) {
	Wrapper wrapper1=LiteralUtils.rasterOrVector(v1);
	Wrapper wrapper2=LiteralUtils.rasterOrVector(v2);
	Double value=v4.getDouble();
	Integer bandnum=v3.getInteger().intValue();
	if(wrapper1 instanceof GeometryWrapper && wrapper2 instanceof GeometryWrapper) {
		throw new RuntimeException("Function only applicable to Vector/Raster Raster/Raster input");
	}else if(wrapper1 instanceof CoverageWrapper && wrapper2 instanceof CoverageWrapper) {
		GridCoverage raster=((CoverageWrapper)wrapper1).getGridGeometry();
		GridCoverage raster2=((CoverageWrapper)wrapper2).getGridGeometry();		
		Geometry bbox1 = LiteralUtils.toGeometry(raster.getGridGeometry().getEnvelope());
	    Geometry bbox2 = LiteralUtils.toGeometry(raster2.getGridGeometry().getEnvelope());
	    if(bbox1.equals(bbox2)) {
			return CoverageWrapper.createCoverage((GridCoverage)raster, ((CoverageWrapper)wrapper1).getSrsURI(), ((CoverageWrapper)wrapper1).getRasterDatatypeURI())
					.asNodeValue();
	    }
	    if(!bbox1.intersects(bbox2)) {
			return LiteralUtils.createEmptyRaster(bbox1.getEnvelopeInternal().getWidth()<=0?1:bbox1.getEnvelopeInternal().getWidth(),bbox1.getEnvelopeInternal().getHeight()<=0?1:bbox1.getEnvelopeInternal().getHeight(), bbox1.getEnvelopeInternal().getMaxX(),bbox1.getEnvelopeInternal().getMaxY(), 0.);
	    }else {
	    	Geometry intersection=bbox1.intersection(bbox2);
		    	try {
					GridCoverage cov = LiteralUtils.cropRaster2((CoverageWrapper)wrapper2, intersection.getEnvelopeInternal().getWidth(), intersection.getEnvelopeInternal().getHeight(), intersection.getEnvelopeInternal().getMaxX(), intersection.getEnvelopeInternal().getMaxY());
					if(LiteralUtils.maxRasterValue(cov, bandnum)<value) {
						return NodeValue.TRUE;
					}
					return NodeValue.FALSE;
		    	} catch (MismatchedDimensionException | TransformException e) {
					throw new RuntimeException("Cropping failed");
				}
	    }	
	}else {
		if(wrapper1 instanceof CoverageWrapper) {
			GridCoverage raster=((CoverageWrapper)wrapper1).getGridGeometry();
			Geometry bbox1 = LiteralUtils.toGeometry(raster.getGridGeometry().getEnvelope());
			Geometry geom=((GeometryWrapper)wrapper2).getXYGeometry();
		    if(!bbox1.intersects(geom)) {
				return LiteralUtils.createEmptyRaster(bbox1.getEnvelopeInternal().getWidth()<=0?1:bbox1.getEnvelopeInternal().getWidth(),bbox1.getEnvelopeInternal().getHeight()<=0?1:bbox1.getEnvelopeInternal().getHeight(), bbox1.getEnvelopeInternal().getMaxX(),bbox1.getEnvelopeInternal().getMaxY(), 0.);
		    }else {
		    	Geometry intersection=bbox1.intersection(geom);
			    	try {
						GridCoverage cov=LiteralUtils.cropRaster2((CoverageWrapper)wrapper1, intersection.getEnvelopeInternal().getWidth(), intersection.getEnvelopeInternal().getHeight(), intersection.getEnvelopeInternal().getMaxX(), intersection.getEnvelopeInternal().getMaxY());
						if(LiteralUtils.maxRasterValue(cov, bandnum)<value) {
							return NodeValue.TRUE;
						}
						return NodeValue.FALSE;
			    	} catch (MismatchedDimensionException | TransformException e) {
						throw new RuntimeException("Cropping failed");
					}
		    }	
		}else {
			GridCoverage raster=((CoverageWrapper)wrapper2).getGridGeometry();
			Geometry bbox1 = LiteralUtils.toGeometry(raster.getGridGeometry().getEnvelope());
			Geometry geom=((GeometryWrapper)wrapper1).getXYGeometry();
		    if(!bbox1.intersects(geom)) {
				return LiteralUtils.createEmptyRaster(bbox1.getEnvelopeInternal().getWidth()<=0?1:bbox1.getEnvelopeInternal().getWidth(),bbox1.getEnvelopeInternal().getHeight()<=0?1:bbox1.getEnvelopeInternal().getHeight(), bbox1.getEnvelopeInternal().getMaxX(),bbox1.getEnvelopeInternal().getMaxY(), 0.);
		    }else {
		    	Geometry intersection=bbox1.intersection(geom);
			    	try {
						GridCoverage cov=LiteralUtils.cropRaster2((CoverageWrapper)wrapper2, intersection.getEnvelopeInternal().getWidth(), intersection.getEnvelopeInternal().getHeight(), intersection.getEnvelopeInternal().getMaxX(), intersection.getEnvelopeInternal().getMaxY());
						if(LiteralUtils.maxRasterValue(cov, bandnum)<value) {
							return NodeValue.TRUE;
						}
						return NodeValue.FALSE;
			    	} catch (MismatchedDimensionException | TransformException e) {
						throw new RuntimeException("Cropping failed");
			    	}				
		    }
	}}}
}
