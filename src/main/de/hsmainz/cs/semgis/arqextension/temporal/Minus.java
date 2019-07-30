package de.hsmainz.cs.semgis.arqextension.temporal;

import java.util.Calendar;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;

import io.github.galbiston.geosparql_jena.implementation.datatype.temporal.TemporalRange;
import io.github.galbiston.geosparql_jena.implementation.datatype.temporal.TemporalRangeWrapper;

public class Minus extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		TemporalRange range1=TemporalRangeWrapper.extract(v1.getString(), v1.getDatatypeURI());
		TemporalRange range2=TemporalRangeWrapper.extract(v2.getString(), v2.getDatatypeURI());
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		start.setTime(range1.to);
		end.setTime(range1.from);
		
		start.set(2010, 7, 23);
		end.set(2010, 8, 26);
		//range2.to. range2.from
		if(range1.from.equals(range2.from) && range1.to.equals(range2.to)) {
			return NodeValue.makeNodeBoolean(true);
		}
		return NodeValue.makeNodeBoolean(false);
	}

}
