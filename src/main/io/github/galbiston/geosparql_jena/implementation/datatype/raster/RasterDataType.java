package io.github.galbiston.geosparql_jena.implementation.datatype.raster;

import org.apache.jena.datatypes.BaseDatatype;
import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.datatypes.RDFDatatype;
import org.apache.jena.datatypes.TypeMapper;

import io.github.galbiston.geosparql_jena.implementation.CoverageWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.GMLDatatype;
import io.github.galbiston.geosparql_jena.implementation.datatype.GeoJSONDatatype;
import io.github.galbiston.geosparql_jena.implementation.datatype.GeometryDatatype;
import io.github.galbiston.geosparql_jena.implementation.datatype.KMLDatatype;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;
import io.github.galbiston.geosparql_jena.implementation.index.GeometryLiteralIndex;
import io.github.galbiston.geosparql_jena.implementation.index.GeometryLiteralIndex.GeometryIndex;

public abstract class RasterDataType extends BaseDatatype {

    public RasterDataType(String uri) {
        super(uri);
    }

    public abstract CoverageWrapper read(String geometryLiteral);

    /**
     * This method Parses the Geometry Literal to the JTS Geometry
     *
     * @param lexicalForm - the Geometry Literal to be parsed
     * @return geometry - if the Geometry Literal is valid.
     * <br> empty geometry - if the Geometry Literal is empty.
     * <br> null - if the Geometry Literal is invalid.
     */
    @Override
    public final CoverageWrapper parse(String lexicalForm) throws DatatypeFormatException {
        return parse(lexicalForm, GeometryIndex.PRIMARY);
    }

    public final CoverageWrapper parse(String lexicalForm, GeometryIndex targetIndex) throws DatatypeFormatException {
        //Check the Geometry Literal Index to see if been previously read and cached.
        //DatatypeReader interface used to instruct index on how to obtain the GeometryWrapper.
        try {
            return GeometryLiteralIndex.retrieve(lexicalForm, this, targetIndex);
        } catch (IllegalArgumentException ex) {
            throw new DatatypeFormatException(ex.getMessage() + " - Illegal Geometry Literal: " + lexicalForm);
        }
    }

    private static final TypeMapper TYPE_MAPPER = TypeMapper.getInstance();
    private static boolean isDatatypesRegistered = false;

    public static final void registerDatatypes() {
        if (!isDatatypesRegistered) {
            TYPE_MAPPER.registerDatatype(CovJSONDatatype.INSTANCE);
        	TYPE_MAPPER.registerDatatype(GeoTIFFDatatype.INSTANCE);
            TYPE_MAPPER.registerDatatype(GMLCOVDatatype.INSTANCE);
            TYPE_MAPPER.registerDatatype(HexWKBRastDatatype.INSTANCE);
            TYPE_MAPPER.registerDatatype(WKBRastDatatype.INSTANCE);
            isDatatypesRegistered = true;
        }
    }

    public static final RasterDataType get(RDFDatatype rdfDatatype) {
        if (rdfDatatype instanceof GeometryDatatype) {
            return (RasterDataType) rdfDatatype;
        } else {
            throw new DatatypeFormatException("Unrecognised Geometry Datatype: " + rdfDatatype.getURI() + " Ensure that Datatype is extending GeometryDatatype.");
        }
    }

    public static final RasterDataType get(String datatypeURI) {
        checkURI(datatypeURI);
        RDFDatatype rdfDatatype = TYPE_MAPPER.getTypeByName(datatypeURI);

        return RasterDataType.get(rdfDatatype);
    }

    public static final boolean checkURI(String datatypeURI) {
        registerDatatypes();
        RDFDatatype rdfDatatype = TYPE_MAPPER.getTypeByName(datatypeURI);
        if (rdfDatatype != null) {
            return rdfDatatype instanceof GeometryDatatype;
        } else {
            throw new DatatypeFormatException("Datatype not found: " + datatypeURI + " Ensure that GeoSPARQL is enabled and Datatype has been registered.");
        }
    }

    public static final boolean check(RDFDatatype rdfDatatype) {
        //Ensure that the registered datatypes from the type mapper are used.
        return checkURI(rdfDatatype.getURI());
    }
}
