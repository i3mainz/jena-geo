package de.hsmainz.cs.semgis.arqextension.temporal;

import java.util.Calendar;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;

import io.github.galbiston.geosparql_jena.implementation.datatype.temporal.TemporalRange;
import io.github.galbiston.geosparql_jena.implementation.datatype.temporal.TemporalRangeWrapper;

public class PeriodEnd extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v1) {
		TemporalRange range1=TemporalRangeWrapper.extract(v1.getString(), v1.getDatatypeURI());
		Calendar cal=Calendar.getInstance();
		return NodeValue.makeDateTime(range1.to.toGMTString());
	}

}
