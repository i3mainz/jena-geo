package de.hsmainz.cs.semgis.arqextension.geometry.relation;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.apache.sis.coverage.grid.GridCoverage;
import org.locationtech.jts.algorithm.match.AreaSimilarityMeasure;
import org.locationtech.jts.geom.Geometry;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import de.hsmainz.cs.semgis.arqextension.util.Wrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

public class AreaSimilarity extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		Wrapper wrapper1=LiteralUtils.rasterOrVector(v1);
		Wrapper wrapper2=LiteralUtils.rasterOrVector(v2);
    	if(wrapper1 instanceof GeometryWrapper) {
		try {
	        GeometryWrapper geometry = GeometryWrapper.extract(v1);
	        Geometry geom = geometry.getParsingGeometry();
	        GeometryWrapper geometry2 = GeometryWrapper.extract(v2);
	        Geometry geom2 = geometry2.getParsingGeometry();
	        AreaSimilarityMeasure areasim=new AreaSimilarityMeasure();
	        return NodeValue.makeDouble(areasim.measure(geom, geom2));
	    } catch (DatatypeFormatException ex) {
	        throw new ExprEvalException(ex.getMessage(), ex);
	    
	}
    	}else if(wrapper1 instanceof CoverageWrapper && wrapper2 instanceof CoverageWrapper) {
			GridCoverage raster=((CoverageWrapper)wrapper1).getGridGeometry();
			GridCoverage raster2=((CoverageWrapper)wrapper2).getGridGeometry();		
			Geometry bbox1 = LiteralUtils.toGeometry(raster.getGridGeometry().getEnvelope());
		    Geometry bbox2 = LiteralUtils.toGeometry(raster2.getGridGeometry().getEnvelope());
		    AreaSimilarityMeasure areasim=new AreaSimilarityMeasure();
	        return NodeValue.makeDouble(areasim.measure(bbox1, bbox2));	
		}else {
			if(wrapper1 instanceof CoverageWrapper) {
				GridCoverage raster=((CoverageWrapper)wrapper1).getGridGeometry();
				Geometry bbox1 = LiteralUtils.toGeometry(raster.getGridGeometry().getEnvelope());
				Geometry geom=((GeometryWrapper)wrapper2).getXYGeometry();
			    AreaSimilarityMeasure areasim=new AreaSimilarityMeasure();
		        return NodeValue.makeDouble(areasim.measure(bbox1, geom));	
			}else {
				GridCoverage raster=((CoverageWrapper)wrapper2).getGridGeometry();
				Geometry bbox1 = LiteralUtils.toGeometry(raster.getGridGeometry().getEnvelope());
				Geometry geom=((GeometryWrapper)wrapper1).getXYGeometry();
			    AreaSimilarityMeasure areasim=new AreaSimilarityMeasure();
		        return NodeValue.makeDouble(areasim.measure(bbox1, geom));		
			}
		}
	}

}
