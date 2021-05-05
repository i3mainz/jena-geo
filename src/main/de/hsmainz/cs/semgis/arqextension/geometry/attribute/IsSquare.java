package de.hsmainz.cs.semgis.arqextension.geometry.attribute;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Geometry;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import de.hsmainz.cs.semgis.arqextension.util.Wrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

public class IsSquare extends FunctionBase1{

	@Override
	public NodeValue exec(NodeValue v) {
		Geometry geom=null;
		Wrapper wrapper1=LiteralUtils.rasterOrVector(v);
    	if(wrapper1 instanceof GeometryWrapper) {
	       try {
	            GeometryWrapper geometry = GeometryWrapper.extract(v);
	            geom = geometry.getParsingGeometry();
	        } catch (DatatypeFormatException ex) {
	            throw new ExprEvalException(ex.getMessage(), ex);
	        }
    	}else if(wrapper1 instanceof CoverageWrapper) {
 	       try {
	            CoverageWrapper geometry = CoverageWrapper.extract(v);
	            geom = LiteralUtils.toGeometry(geometry.getParsingGeometry().getGridGeometry().getEnvelope());
	        } catch (DatatypeFormatException ex) {
	            throw new ExprEvalException(ex.getMessage(), ex);
	        }
    	}
    	if(geom!=null && geom.isRectangle()) {
    		Double distance01=geom.getCoordinates()[0].distance(geom.getCoordinates()[1]);
    		Double distance12=geom.getCoordinates()[1].distance(geom.getCoordinates()[2]);
    		Double distance23=geom.getCoordinates()[2].distance(geom.getCoordinates()[3]);
    		Double distance34=geom.getCoordinates()[3].distance(geom.getCoordinates()[0]);
    		if(distance01==distance23 && distance12==distance34 && distance01==distance12 
    				&& distance23==distance34)
    			return NodeValue.TRUE;
    	}
    	return NodeValue.FALSE;
	}
}
