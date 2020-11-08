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
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

public class IntersectionPercentage extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		Wrapper wrapper1=LiteralUtils.rasterOrVector(v1);
		Wrapper wrapper2=LiteralUtils.rasterOrVector(v2);
		if(wrapper1 instanceof GeometryWrapper && wrapper2 instanceof GeometryWrapper) {
			GeometryWrapper geom1 = GeometryWrapper.extract(v1);
	        GeometryWrapper geom2 = GeometryWrapper.extract(v2);
	    	Double db;
			try {
				db = (geom1.intersection(geom2).getXYGeometry().getArea()/geom2.getXYGeometry().getArea());
		    	if(db.equals(Double.NaN)){
		    		return NodeValue.makeDouble(0.);
		    	}
		        return NodeValue.makeDouble(db);
			} catch (MismatchedDimensionException | FactoryException | TransformException e) {
				// TODO Auto-generated catch block
				return NodeValue.makeString(e.getMessage());
			}
		}else if(wrapper1 instanceof CoverageWrapper && wrapper2 instanceof CoverageWrapper) {
			GridCoverage raster=((CoverageWrapper)wrapper1).getXYGeometry();
			GridCoverage raster2=((CoverageWrapper)wrapper2).getXYGeometry();
			Geometry geom1=LiteralUtils.toGeometry(raster.getGridGeometry().getEnvelope());
			Geometry geom2=LiteralUtils.toGeometry(raster2.getGridGeometry().getEnvelope());
			Double db = (geom1.getArea()/geom2.getArea());
	    	if(db.equals(Double.NaN)){
	    		return NodeValue.makeDouble(0.);
	    	}
	        return NodeValue.makeDouble(db);
		}else {
			Geometry geom;
			Envelope bbox1;
			if(wrapper1 instanceof CoverageWrapper) {
				GridCoverage raster=((CoverageWrapper)wrapper1).getXYGeometry();
				bbox1 = raster.getGridGeometry().getEnvelope();
				geom=((GeometryWrapper)wrapper2).getXYGeometry();
			}else {
				GridCoverage raster=((CoverageWrapper)wrapper2).getXYGeometry();
				bbox1 = raster.getGridGeometry().getEnvelope();
				geom=((GeometryWrapper)wrapper1).getXYGeometry();				
			}
			Double db = (geom.getArea()/LiteralUtils.toGeometry(bbox1).getArea());
	    	if(db.equals(Double.NaN)){
	    		return NodeValue.makeDouble(0.);
	    	}
	        return NodeValue.makeDouble(db);
		}

	}

}
