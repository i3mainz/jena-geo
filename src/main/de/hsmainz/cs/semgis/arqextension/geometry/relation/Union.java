package de.hsmainz.cs.semgis.arqextension.geometry.relation;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.apache.sis.coverage.grid.GridCoverage;
import org.apache.sis.geometry.Envelope2D;
import org.locationtech.jts.geom.Geometry;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import de.hsmainz.cs.semgis.arqextension.util.Wrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

public class Union extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		Wrapper wrapper1=LiteralUtils.rasterOrVector(v1);
		Wrapper wrapper2=LiteralUtils.rasterOrVector(v2);
		if(wrapper1 instanceof GeometryWrapper && wrapper2 instanceof GeometryWrapper) {
			try {
	            GeometryWrapper geometry = GeometryWrapper.extract(v1);
	            Geometry geom = geometry.getXYGeometry();
	            GeometryWrapper geometry2 = GeometryWrapper.extract(v2);
	            GeometryWrapper transGeom2 = geometry2.transform(geometry.getSRID());
	            Geometry uniongeom=geom.union(transGeom2.getXYGeometry());
	            GeometryWrapper unionWrapper = GeometryWrapperFactory.createGeometry(uniongeom, geometry.getSrsURI(), geometry.getGeometryDatatypeURI());
	            return unionWrapper.asNodeValue();
	        } catch (DatatypeFormatException | MismatchedDimensionException | TransformException | FactoryException ex) {
	            throw new ExprEvalException(ex.getMessage(), ex);
	        }
		}else if(wrapper1 instanceof CoverageWrapper && wrapper2 instanceof CoverageWrapper) {
			GridCoverage raster=((CoverageWrapper)wrapper1).getXYGeometry();
			GridCoverage raster2=((CoverageWrapper)wrapper2).getXYGeometry();	
			Geometry uniongeom=LiteralUtils.toGeometry(raster.getGridGeometry().getEnvelope()).
					union(LiteralUtils.toGeometry(raster2.getGridGeometry().getEnvelope()));
	        GeometryWrapper unionWrapper = GeometryWrapperFactory.createGeometry(uniongeom, WKTDatatype.URI);
	        return unionWrapper.asNodeValue();		
		}else {
			if(wrapper1 instanceof CoverageWrapper) {
				GridCoverage raster=((CoverageWrapper)wrapper1).getXYGeometry();
				org.opengis.geometry.Envelope bbox1 = raster.getGridGeometry().getEnvelope();
				Geometry geom=((GeometryWrapper)wrapper2).getXYGeometry();
				Geometry uniongeom=geom.union(LiteralUtils.toGeometry(raster.getGridGeometry().getEnvelope()));
		        GeometryWrapper unionWrapper = GeometryWrapperFactory.createGeometry(uniongeom, WKTDatatype.URI);
		        return unionWrapper.asNodeValue();		
			}else {
				GridCoverage raster=((CoverageWrapper)wrapper2).getXYGeometry();
				org.opengis.geometry.Envelope bbox1 = raster.getGridGeometry().getEnvelope();
				Geometry geom=((GeometryWrapper)wrapper1).getXYGeometry();
				Geometry uniongeom=geom.union(LiteralUtils.toGeometry(raster.getGridGeometry().getEnvelope()));
		        GeometryWrapper unionWrapper = GeometryWrapperFactory.createGeometry(uniongeom, WKTDatatype.URI);
		        return unionWrapper.asNodeValue();			
			}
		}

	}

}
