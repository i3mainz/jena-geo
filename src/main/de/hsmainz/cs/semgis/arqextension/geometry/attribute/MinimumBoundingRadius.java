package de.hsmainz.cs.semgis.arqextension.geometry.attribute;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.locationtech.jts.geom.Geometry;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import de.hsmainz.cs.semgis.arqextension.util.Wrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

/**
 * Returns the center point and radius of the smallest circle that can fully contain a geometry.
 *
 */
public class MinimumBoundingRadius extends FunctionBase1 {

    @Override
    public NodeValue exec(NodeValue arg0) {
    	Wrapper wrapper1=LiteralUtils.rasterOrVector(arg0);
    	if(wrapper1 instanceof GeometryWrapper) {
        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            Geometry geom = geometry.getXYGeometry();
            org.locationtech.jts.algorithm.MinimumBoundingCircle minCircle = new org.locationtech.jts.algorithm.MinimumBoundingCircle(geom);
                return NodeValue.makeDouble(minCircle.getRadius());
            } catch (DatatypeFormatException ex) {
                throw new ExprEvalException(ex.getMessage(), ex);
            }
    }else if(wrapper1 instanceof CoverageWrapper) {
 		 CoverageWrapper covwrap = CoverageWrapper.extract(arg0);
       GridCoverage2D cov = covwrap.getXYGeometry();
       Geometry geom=LiteralUtils.toGeometry(cov.getGridGeometry().getEnvelope());
       org.locationtech.jts.algorithm.MinimumBoundingCircle minCircle = new org.locationtech.jts.algorithm.MinimumBoundingCircle(geom);
       return NodeValue.makeDouble(minCircle.getRadius());
	}else {
		throw new ExprEvalException("No geometry or coverage literal");
	}
    }

}
