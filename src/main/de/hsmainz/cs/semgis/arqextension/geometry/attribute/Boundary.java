package de.hsmainz.cs.semgis.arqextension.geometry.attribute;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.apache.sis.coverage.grid.GridCoverage;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.operation.BoundaryOp;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import de.hsmainz.cs.semgis.arqextension.util.Wrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

/**
 * Returns the closure of the combinatorial boundary of this Geometry.
 *
 */
public class Boundary extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
    	Wrapper wrapper1=LiteralUtils.rasterOrVector(v);
    	if(wrapper1 instanceof GeometryWrapper) {
        try {
            try {
                GeometryWrapper geometry = GeometryWrapper.extract(v);
                Geometry geom = geometry.getParsingGeometry();
                BoundaryOp boundop=new BoundaryOp(geom);
                GeometryWrapper wrapper = GeometryWrapperFactory.createGeometry(boundop.getBoundary(), geometry.getSrsURI(), geometry.getGeometryDatatypeURI());
                return wrapper.asNodeValue();                 
            } catch (DatatypeFormatException ex) {
                throw new ExprEvalException(ex.getMessage(), ex);
            }
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
    	}else if(wrapper1 instanceof CoverageWrapper) {
    		 CoverageWrapper covwrap = CoverageWrapper.extract(v);
             GridCoverage cov = covwrap.getXYGeometry();
             Geometry geom=LiteralUtils.toGeometry(cov.getGridGeometry().getEnvelope());
             BoundaryOp boundop=new BoundaryOp(geom);
             GeometryWrapper wrapper = GeometryWrapperFactory.createGeometry(boundop.getBoundary(), WKTDatatype.URI);
             return wrapper.asNodeValue();                 
    	}else {
    		return NodeValue.makeDouble(0.);
    	}
		

	}

}
