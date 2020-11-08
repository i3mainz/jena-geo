package de.hsmainz.cs.semgis.arqextension.envelope.relation;

import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.apache.sis.coverage.grid.GridCoverage;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import de.hsmainz.cs.semgis.arqextension.util.Wrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

/**
 * Returns TRUE if A's bounding box is strictly to the right of B's.
 *
 */
public class BBOXRightOf extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		Wrapper wrapper1=LiteralUtils.rasterOrVector(v1);
		Wrapper wrapper2=LiteralUtils.rasterOrVector(v2);
		if(wrapper1 instanceof GeometryWrapper && wrapper2 instanceof GeometryWrapper) {
        try {
            GeometryWrapper geom = GeometryWrapper.extract(v1);
            GeometryWrapper geom2 = GeometryWrapper.extract(v2);
			GeometryWrapper transGeom2 = geom2.transform(geom.getSRID());
			if(geom.getEnvelope().getMinX()>transGeom2.getEnvelope().getMaxX()) {
				return NodeValue.TRUE;
			}
			return NodeValue.FALSE;
		} catch (MismatchedDimensionException | TransformException | FactoryException e) {
			// TODO Auto-generated catch block
			throw new ExprEvalException(e.getMessage(), e);
		}
		}else if(wrapper1 instanceof CoverageWrapper && wrapper2 instanceof CoverageWrapper) {
    		GridCoverage raster=((CoverageWrapper)wrapper1).getGridGeometry();
    		GridCoverage raster2=((CoverageWrapper)wrapper2).getGridGeometry();		
            try {
    			if(LiteralUtils.toGeometry(raster.getGridGeometry().getEnvelope()).
    					intersects(LiteralUtils.toGeometry(raster2.getGridGeometry().getEnvelope()))) {
    				return NodeValue.TRUE;
    			}
    			if(raster.getGridGeometry().getEnvelope().getLowerCorner().getCoordinate()[0]>
    					raster2.getGridGeometry().getEnvelope().getUpperCorner().getCoordinate()[0]) {
    						return NodeValue.TRUE;
    			}
    			return NodeValue.FALSE;
    		} catch (MismatchedDimensionException e) {
    			// TODO Auto-generated catch block
    			throw new ExprEvalException(e.getMessage(), e);
    		}
    	}else {
    		if(wrapper1 instanceof CoverageWrapper) {
    			GridCoverage raster=((CoverageWrapper)wrapper1).getGridGeometry();
    			GeometryWrapper geom2 = GeometryWrapper.extract(v2);
    			if(LiteralUtils.toGeometry(raster.getGridGeometry().getEnvelope()).intersects(LiteralUtils.toGeometry(geom2.getEnvelope()))){
    				return NodeValue.TRUE;
    			}
				if(raster.getGridGeometry().getEnvelope().getLowerCorner().getCoordinate()[0]>geom2.getEnvelope().getMaxX()) {
					return NodeValue.TRUE;
				}
				return NodeValue.FALSE;
    		}else {
    			GridCoverage raster=((CoverageWrapper)wrapper2).getGridGeometry();
    			GeometryWrapper geom2 = GeometryWrapper.extract(v1);		
    			if(LiteralUtils.toGeometry(raster.getGridGeometry().getEnvelope()).intersects(LiteralUtils.toGeometry(geom2.getEnvelope()))) {
    				return NodeValue.TRUE;
    			}
				if(raster.getGridGeometry().getEnvelope().getLowerCorner().getCoordinate()[0]>geom2.getEnvelope().getMaxX()) {
					return NodeValue.TRUE;
				}
				return NodeValue.FALSE;
    		}
    	}
	}

}
