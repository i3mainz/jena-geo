package io.github.galbiston.geosparql_jena.implementation.datatype.raster;

import de.hsmainz.cs.semgis.arqextension.vocabulary.PostGISGeo;
import io.github.galbiston.geosparql_jena.implementation.CoverageWrapper;

public class GMLCOVDatatype extends RasterDataType {
	
	public GMLCOVDatatype() {
		super(URI);
		// TODO Auto-generated constructor stub
	}
	public static final String URI=PostGISGeo.GMLCOV;
	
	public static final GMLCOVDatatype INSTANCE=new GMLCOVDatatype();

	@Override
	public CoverageWrapper read(String geometryLiteral) {
		// TODO Auto-generated method stub
		return null;
	}

}
