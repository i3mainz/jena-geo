package de.hsmainz.cs.semgis.arqextension.geometry.attribute;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.apache.sis.coverage.grid.GridCoverage;
import org.apache.sis.referencing.CRS;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import de.hsmainz.cs.semgis.arqextension.util.Wrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

public class HasHorizontalCRS extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
		Wrapper wrapper1=LiteralUtils.rasterOrVector(v);
    	if(wrapper1 instanceof GeometryWrapper) {
    		GeometryWrapper geometry = GeometryWrapper.extract(v);
    		return NodeValue.makeBoolean(CRS.isHorizontalCRS(geometry.getCRS()));
    	}else if(wrapper1 instanceof CoverageWrapper) {
			GridCoverage raster=((CoverageWrapper)wrapper1).getGridGeometry();
			return NodeValue.makeBoolean(CRS.isHorizontalCRS(raster.getGridGeometry().getCoordinateReferenceSystem()));
		}
    	return NodeValue.FALSE;
	}
}
