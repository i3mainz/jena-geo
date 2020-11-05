package de.hsmainz.cs.semgis.arqextension.raster.relation;


import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.locationtech.jts.geom.Geometry;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import de.hsmainz.cs.semgis.arqextension.util.Wrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

public class RasterIntersection extends FunctionBase3 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2,NodeValue v3) {
		Wrapper wrapper1=LiteralUtils.rasterOrVector(v1);
		Wrapper wrapper2=LiteralUtils.rasterOrVector(v2);
		Boolean second=v3.getBoolean();
		if(wrapper1 instanceof GeometryWrapper && wrapper2 instanceof GeometryWrapper) {
			GeometryWrapper transGeom2;
			try {
				transGeom2 = ((GeometryWrapper)wrapper2).transform(((GeometryWrapper)wrapper1).getSrsInfo());
			    if(!((GeometryWrapper)wrapper1).getXYGeometry().intersects(transGeom2.getXYGeometry())) {
			    	Geometry env=((GeometryWrapper)wrapper1).getXYGeometry().getEnvelope();
					return LiteralUtils.createEmptyRaster(env.getEnvelopeInternal().getWidth(),env.getEnvelopeInternal().getHeight(), env.getEnvelopeInternal().getMaxX(), env.getEnvelopeInternal().getMaxY(), 0.);
			    }else {
			    	Geometry env=((GeometryWrapper)wrapper1).getXYGeometry().intersection(transGeom2.getXYGeometry()).getEnvelope();
			    	return LiteralUtils.createEmptyRaster(env.getEnvelopeInternal().getWidth(),env.getEnvelopeInternal().getHeight(), env.getEnvelopeInternal().getMaxX(), env.getEnvelopeInternal().getMaxY(), 0.);
			    }	
			} catch (MismatchedDimensionException | TransformException | FactoryException e) {
				throw new RuntimeException("CRS transformation failed");
			}
		}else if(wrapper1 instanceof CoverageWrapper && wrapper2 instanceof CoverageWrapper) {
			GridCoverage2D raster=((CoverageWrapper)wrapper1).getGridGeometry();
			GridCoverage2D raster2=((CoverageWrapper)wrapper2).getGridGeometry();		
			Geometry bbox1 = LiteralUtils.toGeometry(raster.getGridGeometry().getEnvelope());
		    Geometry bbox2 = LiteralUtils.toGeometry(raster2.getGridGeometry().getEnvelope());
		    if(bbox1.equals(bbox2)) {
				return CoverageWrapper.createCoverage((GridCoverage2D)raster, ((CoverageWrapper)wrapper1).getSrsURI(), ((CoverageWrapper)wrapper1).getRasterDatatypeURI())
						.asNodeValue();
		    }
		    if(!bbox1.intersects(bbox2)) {
				return LiteralUtils.createEmptyRaster(bbox1.getEnvelopeInternal().getWidth()<=0?1:bbox1.getEnvelopeInternal().getWidth(),bbox1.getEnvelopeInternal().getHeight()<=0?1:bbox1.getEnvelopeInternal().getHeight(), bbox1.getEnvelopeInternal().getMaxX(),bbox1.getEnvelopeInternal().getMaxY(), 0.);
		    }else {
		    	Geometry intersection=bbox1.intersection(bbox2);
		    	if(second) {
			    	try {
						return LiteralUtils.cropRaster((CoverageWrapper)wrapper2, intersection.getEnvelopeInternal().getWidth(), intersection.getEnvelopeInternal().getHeight(), intersection.getEnvelopeInternal().getMaxX(), intersection.getEnvelopeInternal().getMaxY());
					} catch (MismatchedDimensionException | TransformException e) {
						throw new RuntimeException("Cropping failed");
					}
		    	}else {
			    	try {
						return LiteralUtils.cropRaster((CoverageWrapper)wrapper1, intersection.getEnvelopeInternal().getWidth(), intersection.getEnvelopeInternal().getHeight(), intersection.getEnvelopeInternal().getMaxX(), intersection.getEnvelopeInternal().getMaxY());
					} catch (MismatchedDimensionException | TransformException e) {
						throw new RuntimeException("Cropping failed");
					}
		    	}

		    }	
		}else {
			if(wrapper1 instanceof CoverageWrapper) {
				GridCoverage2D raster=((CoverageWrapper)wrapper1).getGridGeometry();
				Geometry bbox1 = LiteralUtils.toGeometry(raster.getGridGeometry().getEnvelope());
				Geometry geom=((GeometryWrapper)wrapper2).getXYGeometry();
			    if(!bbox1.intersects(geom)) {
					return LiteralUtils.createEmptyRaster(bbox1.getEnvelopeInternal().getWidth()<=0?1:bbox1.getEnvelopeInternal().getWidth(),bbox1.getEnvelopeInternal().getHeight()<=0?1:bbox1.getEnvelopeInternal().getHeight(), bbox1.getEnvelopeInternal().getMaxX(),bbox1.getEnvelopeInternal().getMaxY(), 0.);
			    }else {
			    	Geometry intersection=bbox1.intersection(geom);
			    	if(second) {
				    	try {
							return LiteralUtils.cropRaster((CoverageWrapper)wrapper2, intersection.getEnvelopeInternal().getWidth(), intersection.getEnvelopeInternal().getHeight(), intersection.getEnvelopeInternal().getMaxX(), intersection.getEnvelopeInternal().getMaxY());
						} catch (MismatchedDimensionException | TransformException e) {
							throw new RuntimeException("Cropping failed");
						}
			    	}else {
				    	try {
							return LiteralUtils.cropRaster((CoverageWrapper)wrapper1, intersection.getEnvelopeInternal().getWidth(), intersection.getEnvelopeInternal().getHeight(), intersection.getEnvelopeInternal().getMaxX(), intersection.getEnvelopeInternal().getMaxY());
						} catch (MismatchedDimensionException | TransformException e) {
							throw new RuntimeException("Cropping failed");
						}
			    	}

			    }	
			}else {
				GridCoverage2D raster=((CoverageWrapper)wrapper2).getGridGeometry();
				Geometry bbox1 = LiteralUtils.toGeometry(raster.getGridGeometry().getEnvelope());
				Geometry geom=((GeometryWrapper)wrapper1).getXYGeometry();
			    if(!bbox1.intersects(geom)) {
					return LiteralUtils.createEmptyRaster(bbox1.getEnvelopeInternal().getWidth()<=0?1:bbox1.getEnvelopeInternal().getWidth(),bbox1.getEnvelopeInternal().getHeight()<=0?1:bbox1.getEnvelopeInternal().getHeight(), bbox1.getEnvelopeInternal().getMaxX(),bbox1.getEnvelopeInternal().getMaxY(), 0.);
			    }else {
			    	Geometry intersection=bbox1.intersection(geom);
			    	if(second) {
				    	try {
							return LiteralUtils.cropRaster((CoverageWrapper)wrapper2, intersection.getEnvelopeInternal().getWidth(), intersection.getEnvelopeInternal().getHeight(), intersection.getEnvelopeInternal().getMaxX(), intersection.getEnvelopeInternal().getMaxY());
						} catch (MismatchedDimensionException | TransformException e) {
							throw new RuntimeException("Cropping failed");
						}
			    	}else {
				    	try {
							return LiteralUtils.cropRaster((CoverageWrapper)wrapper1, intersection.getEnvelopeInternal().getWidth(), intersection.getEnvelopeInternal().getHeight(), intersection.getEnvelopeInternal().getMaxX(), intersection.getEnvelopeInternal().getMaxY());
						} catch (MismatchedDimensionException | TransformException e) {
							throw new RuntimeException("Cropping failed");
						}
			    	}

			    }				
			}
		}
	}

}
