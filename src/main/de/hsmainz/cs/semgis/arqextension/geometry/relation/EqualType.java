package de.hsmainz.cs.semgis.arqextension.geometry.relation;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import de.hsmainz.cs.semgis.arqextension.util.Wrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

public class EqualType extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {	
		Wrapper wrapper1=LiteralUtils.rasterOrVector(v1);
		Wrapper wrapper2=LiteralUtils.rasterOrVector(v2);
		if(wrapper1 instanceof GeometryWrapper && wrapper2 instanceof GeometryWrapper) {
			if(((GeometryWrapper)wrapper1).getGeometryType().equals(((GeometryWrapper)wrapper2).getGeometryType())) {
				return NodeValue.TRUE;
			}else {
				return NodeValue.FALSE;
			}
		}else if(wrapper1 instanceof CoverageWrapper && wrapper2 instanceof CoverageWrapper) {
			return NodeValue.TRUE;
		}else {
			return NodeValue.FALSE;
		}
	}

}
