package io.github.galbiston.geosparql_jena.implementation.datatype.temporal;

import org.apache.jena.datatypes.DatatypeFormatException;

public class TemporalRangeWrapper {

    public static TemporalRange extract(String lexicalForm, String datatypeURI) throws DatatypeFormatException {

        if (lexicalForm == null || datatypeURI == null) {
            throw new DatatypeFormatException("GeometryWrapper extraction: arguments cannot be null - " + lexicalForm + ", " + datatypeURI);
        }

        TemporalRangeDatatype datatype = TemporalRangeDatatype.INSTANCE;
        TemporalRange geometry = (TemporalRange) datatype.parse(lexicalForm);
        return geometry;
    }
	
}
