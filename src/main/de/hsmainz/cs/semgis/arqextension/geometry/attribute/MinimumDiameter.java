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

public class MinimumDiameter extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
		Wrapper wrapper1=LiteralUtils.rasterOrVector(v);
    	if(wrapper1 instanceof GeometryWrapper) {
		try {
	        GeometryWrapper geometry = GeometryWrapper.extract(v);
	        Geometry geom = geometry.getParsingGeometry();
	        org.locationtech.jts.algorithm.MinimumDiameter mindiam=new org.locationtech.jts.algorithm.MinimumDiameter(geom);
	        return NodeValue.makeDouble(mindiam.getDiameter().getLength());
	    } catch (DatatypeFormatException ex) {
	        throw new ExprEvalException(ex.getMessage(), ex);
	    }
		
	} else if(wrapper1 instanceof CoverageWrapper) {
		 CoverageWrapper covwrap = CoverageWrapper.extract(v);
     GridCoverage2D cov = covwrap.getXYGeometry();
     Geometry geom=LiteralUtils.toGeometry(cov.getGridGeometry().getEnvelope());
     org.locationtech.jts.algorithm.MinimumDiameter mindiam=new org.locationtech.jts.algorithm.MinimumDiameter(geom);
     return NodeValue.makeDouble(mindiam.getDiameter().getLength());
	}else {
		throw new ExprEvalException("No geometry or coverage literal");
	}
	}


}
