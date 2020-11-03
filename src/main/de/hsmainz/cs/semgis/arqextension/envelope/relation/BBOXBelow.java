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
 * Returns TRUE if A's bounding box is strictly below B's.
 *
 */
public class BBOXBelow extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		Wrapper wrapper1=LiteralUtils.rasterOrVector(v1);
		Wrapper wrapper2=LiteralUtils.rasterOrVector(v2);
		if(wrapper1 instanceof GeometryWrapper && wrapper2 instanceof GeometryWrapper) {
		try {
            GeometryWrapper geom = GeometryWrapper.extract(v1);
            GeometryWrapper geom2 = GeometryWrapper.extract(v2);
			GeometryWrapper transGeom2 = geom2.transform(geom.getSRID());
			System.out.println("BBOX Below");
			if(geom.getEnvelope().getMaxY()<transGeom2.getEnvelope().getMinY()) {
				return NodeValue.TRUE;
			}
			return NodeValue.FALSE;
			} catch (MismatchedDimensionException | TransformException | FactoryException e) {
			return NodeValue.makeString(e.getMessage());
			}
		}else if(wrapper1 instanceof CoverageWrapper && wrapper2 instanceof CoverageWrapper) {
			GridCoverage2D raster=((CoverageWrapper)wrapper1).getGridGeometry();
			GridCoverage2D raster2=((CoverageWrapper)wrapper2).getGridGeometry();		
	        try {
				if(raster.getGridGeometry().getEnvelope().getUpperCorner().getCoordinate()[1]<
				raster2.getGridGeometry().getEnvelope().getLowerCorner().getCoordinate()[1]) {
					return NodeValue.TRUE;
				}
				return NodeValue.FALSE;
			} catch (MismatchedDimensionException e) {
				// TODO Auto-generated catch block
				throw new ExprEvalException(e.getMessage(), e);
			}
		}else {
			if(wrapper1 instanceof CoverageWrapper) {
				GridCoverage2D raster=((CoverageWrapper)wrapper1).getGridGeometry();
				GeometryWrapper geom2 = GeometryWrapper.extract(v2);
				if(raster.getEnvelope().getUpperCorner().getCoordinate()[1]<geom2.getEnvelope().getMinY()) {
					return NodeValue.TRUE;
				}
				return NodeValue.FALSE;
			}else {
				GridCoverage2D raster=((CoverageWrapper)wrapper2).getGridGeometry();
				GeometryWrapper geom2 = GeometryWrapper.extract(v1);
				if(raster.getEnvelope().getUpperCorner().getCoordinate()[1]<geom2.getEnvelope().getMinY()) {
					return NodeValue.TRUE;
				}
				return NodeValue.FALSE;		
			}
		}
	}

}
