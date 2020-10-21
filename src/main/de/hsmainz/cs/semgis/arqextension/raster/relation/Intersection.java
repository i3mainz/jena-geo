package de.hsmainz.cs.semgis.arqextension.raster.relation;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.locationtech.jts.geom.Geometry;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import de.hsmainz.cs.semgis.arqextension.util.Wrapper;
import de.hsmainz.cs.semgis.arqextension.vocabulary.WKT;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

public class Intersection extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		Wrapper wrapper1=LiteralUtils.rasterOrVector(v1);
		Wrapper wrapper2=LiteralUtils.rasterOrVector(v2);
		if(wrapper1 instanceof GeometryWrapper && wrapper2 instanceof GeometryWrapper) {
			GeometryWrapper transGeom2;
			try {
				transGeom2 = ((GeometryWrapper)wrapper2).transform(((GeometryWrapper)wrapper1).getSrsInfo());
				return GeometryWrapperFactory.createGeometry(((GeometryWrapper)wrapper1).getXYGeometry().intersection(transGeom2.getXYGeometry()), ((GeometryWrapper)wrapper1).getGeometryDatatypeURI()).asNodeValue();
			} catch (MismatchedDimensionException | TransformException | FactoryException e) {
				throw new RuntimeException("CRS transformation failed");
			}
		}else if(wrapper1 instanceof CoverageWrapper && wrapper2 instanceof CoverageWrapper) {
			GridCoverage2D raster=((CoverageWrapper)wrapper1).getGridGeometry();
			GridCoverage2D raster2=((CoverageWrapper)wrapper2).getGridGeometry();		
			Geometry bbox1 = LiteralUtils.toGeometry(raster.getGridGeometry().getEnvelope());
		    Geometry bbox2 = LiteralUtils.toGeometry(raster2.getGridGeometry().getEnvelope());
		    return GeometryWrapperFactory.createGeometry(bbox1.intersection(bbox2),WKT.DATATYPE_URI).asNodeValue();		
		}else {
			if(wrapper1 instanceof CoverageWrapper) {
				GridCoverage2D raster=((CoverageWrapper)wrapper1).getGridGeometry();
				Geometry bbox1 = LiteralUtils.toGeometry(raster.getGridGeometry().getEnvelope());
				Geometry geom=((GeometryWrapper)wrapper2).getXYGeometry();
				return GeometryWrapperFactory.createGeometry(bbox1.intersection(geom),WKT.DATATYPE_URI).asNodeValue();
			}else {
				GridCoverage2D raster=((CoverageWrapper)wrapper2).getGridGeometry();
				Geometry bbox1 = LiteralUtils.toGeometry(raster.getGridGeometry().getEnvelope());
				Geometry geom=((GeometryWrapper)wrapper1).getXYGeometry();
				return GeometryWrapperFactory.createGeometry(geom.intersection(bbox1),WKT.DATATYPE_URI).asNodeValue();				
			}
		}
	}

}
