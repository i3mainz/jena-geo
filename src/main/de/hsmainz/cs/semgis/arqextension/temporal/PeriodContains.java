package de.hsmainz.cs.semgis.arqextension.temporal;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;

import io.github.galbiston.geosparql_jena.implementation.datatype.temporal.TemporalRange;
import io.github.galbiston.geosparql_jena.implementation.datatype.temporal.TemporalRangeWrapper;

public class PeriodContains extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		TemporalRange range1=TemporalRangeWrapper.extract(v1.getString(), v1.getDatatypeURI());
		TemporalRange range2=TemporalRangeWrapper.extract(v2.getString(), v2.getDatatypeURI());
		if(range2.from.after(range1.from) && range2.to.before(range1.to)) {
			return NodeValue.makeNodeBoolean(true);
		}
		return NodeValue.makeNodeBoolean(false);
	}

}
