package de.hsmainz.cs.semgis.arqextension.envelope.relation;

import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import de.hsmainz.cs.semgis.arqextension.util.Wrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

/**
 * Returns TRUE if A's bounding box is contained by B's
 *
 */
public class BBOXIsContainedBy extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		Wrapper wrapper1=LiteralUtils.rasterOrVector(v1);
		Wrapper wrapper2=LiteralUtils.rasterOrVector(v2);
		if(wrapper1 instanceof GeometryWrapper && wrapper2 instanceof GeometryWrapper) {
		try {
            GeometryWrapper geom = GeometryWrapper.extract(v1);
            GeometryWrapper geom2 = GeometryWrapper.extract(v2);
			GeometryWrapper transGeom2 = geom2.transform(geom.getSRID());
			return NodeValue.makeBoolean(transGeom2.getEnvelope().contains(geom.getEnvelope()));
		} catch (MismatchedDimensionException | TransformException | FactoryException e) {
			// TODO Auto-generated catch block
			throw new ExprEvalException(e.getMessage(), e);
		}
		}else if(wrapper1 instanceof CoverageWrapper && wrapper2 instanceof CoverageWrapper) {
    		GridCoverage2D raster=((CoverageWrapper)wrapper1).getGridGeometry();
    		GridCoverage2D raster2=((CoverageWrapper)wrapper2).getGridGeometry();		
            try {
    			return NodeValue.makeBoolean(LiteralUtils.toGeometry(raster2.getGridGeometry().getEnvelope()).
    					contains(LiteralUtils.toGeometry(raster.getGridGeometry().getEnvelope())));
    		} catch (MismatchedDimensionException e) {
    			// TODO Auto-generated catch block
    			throw new ExprEvalException(e.getMessage(), e);
    		}
    	}else {
    		if(wrapper1 instanceof CoverageWrapper) {
    			GridCoverage2D raster=((CoverageWrapper)wrapper1).getGridGeometry();
    			GeometryWrapper geom2 = GeometryWrapper.extract(v2);
    			return NodeValue.makeBoolean(LiteralUtils.toGeometry(geom2.getEnvelope()).
    					contains(LiteralUtils.toGeometry(raster.getEnvelope2D())));
    		}else {
    			GridCoverage2D raster=((CoverageWrapper)wrapper2).getGridGeometry();
    			GeometryWrapper geom2 = GeometryWrapper.extract(v1);		
    			return NodeValue.makeBoolean(LiteralUtils.toGeometry(raster.getEnvelope2D()).contains(LiteralUtils.toGeometry(geom2.getEnvelope()))); 
    		}
    	}
	}

}
