package io.github.galbiston.geosparql_jena.implementation.datatype.temporal;

import java.util.Date;

public class TemporalRange {

	public Date from;
	
	public Date to;
	
	public TemporalRange(Date from,Date to) {
		this.from=from;
		this.to=to;
	}
	
}
