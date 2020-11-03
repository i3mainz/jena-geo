package de.hsmainz.cs.semgis.arqextension.geometry.relation;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.operation.distance.DistanceOp;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import de.hsmainz.cs.semgis.arqextension.util.Wrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

public class Distance extends FunctionBase2{

	@Override
	public NodeValue exec(NodeValue arg0, NodeValue arg1) {
		Wrapper wrapper1=LiteralUtils.rasterOrVector(arg0);
		Wrapper wrapper2=LiteralUtils.rasterOrVector(arg1);
    	if(wrapper1 instanceof GeometryWrapper) {
        try {
            GeometryWrapper geom1 = GeometryWrapper.extract(arg0);
            GeometryWrapper geom2 = GeometryWrapper.extract(arg1);
            GeometryWrapper transGeom2 = geom2.transform(geom1.getSrsInfo());
            DistanceOp op=new DistanceOp(geom1.getXYGeometry(), transGeom2.getXYGeometry());
            double distance=op.distance();
            return NodeValue.makeDouble(distance);
        } catch (DatatypeFormatException | FactoryException | MismatchedDimensionException | TransformException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
    	}else if(wrapper1 instanceof CoverageWrapper && wrapper2 instanceof CoverageWrapper) {
			GridCoverage2D raster=((CoverageWrapper)wrapper1).getGridGeometry();
			GridCoverage2D raster2=((CoverageWrapper)wrapper2).getGridGeometry();		
			Geometry bbox1 = LiteralUtils.toGeometry(raster.getGridGeometry().getEnvelope());
		    Geometry bbox2 = LiteralUtils.toGeometry(raster2.getGridGeometry().getEnvelope());
		    return NodeValue.makeDouble(bbox1.distance(bbox2));	
		}else {
			if(wrapper1 instanceof CoverageWrapper) {
				GridCoverage2D raster=((CoverageWrapper)wrapper1).getGridGeometry();
				Geometry bbox1 = LiteralUtils.toGeometry(raster.getGridGeometry().getEnvelope());
				Geometry geom=((GeometryWrapper)wrapper2).getXYGeometry();
			    return NodeValue.makeDouble(bbox1.distance(geom));	
			}else {
				GridCoverage2D raster=((CoverageWrapper)wrapper2).getGridGeometry();
				Geometry bbox1 = LiteralUtils.toGeometry(raster.getGridGeometry().getEnvelope());
				Geometry geom=((GeometryWrapper)wrapper1).getXYGeometry();
			    return NodeValue.makeDouble(bbox1.distance(geom));			
			}
		}
	}

}
