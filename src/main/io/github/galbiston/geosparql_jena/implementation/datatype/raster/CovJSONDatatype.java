package io.github.galbiston.geosparql_jena.implementation.datatype.raster;

import de.hsmainz.cs.semgis.arqextension.vocabulary.PostGISGeo;
import io.github.galbiston.geosparql_jena.implementation.CoverageWrapper;
import uk.ac.rdg.resc.edal.covjson.CoverageJsonConverterImpl;
import uk.ac.rdg.resc.edal.covjson.CoverageJsonWriter;
import uk.ac.rdg.resc.edal.covjson.StreamingEncoder;
import uk.ac.rdg.resc.edal.covjson.writers.Coverage;
import uk.ac.rdg.resc.edal.feature.Feature;

public class CovJSONDatatype extends RasterDataType{

	public static final String URI = PostGISGeo.CoverageJSON;
	
	public static final CovJSONDatatype INSTANCE=new CovJSONDatatype();

	public CovJSONDatatype() {
		super(URI);
	}

	@Override
	public CoverageWrapper read(String geometryLiteral) {
		CoverageJsonConverterImpl covjsonconverter=new CoverageJsonConverterImpl();
		Feature feat;
		Coverage coverage;
		covjsonconverter.convertFeatureToJson(os, feat);
	}
	
	@Override
	public String unparse(Object value) {
		CoverageJsonWriter writer=new CoverageJsonWriter(new StreamingEncoder());
		// TODO Auto-generated method stub
		return super.unparse(value);
	}

}
