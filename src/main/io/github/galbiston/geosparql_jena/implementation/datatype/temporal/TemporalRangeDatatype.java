package io.github.galbiston.geosparql_jena.implementation.datatype.temporal;

import java.util.Date;

import org.apache.jena.datatypes.BaseDatatype;
import org.apache.jena.datatypes.DatatypeFormatException;

import de.hsmainz.cs.semgis.arqextension.vocabulary.PostGISGeo;

public class TemporalRangeDatatype extends BaseDatatype {

	public static final String URI = PostGISGeo.TemporalRange;
	
    /**
     * A static instance of WKTDatatype.
     */
    public static final TemporalRangeDatatype INSTANCE = new TemporalRangeDatatype();

	public TemporalRangeDatatype() {
		super(URI);
	}
	
	@Override
	public String unparse(Object value) {
		return ((TemporalRange)value).from.getTime()/1000+";"+((TemporalRange)value).to.getTime()/1000;
	}
	
	
	@Override
	public Object parse(String lexicalForm) throws DatatypeFormatException {
		String[] dates=lexicalForm.split(";");
		TemporalRange range=new TemporalRange(new Date(dates[0]),new Date(dates[1]));
		return range;
	}

}
