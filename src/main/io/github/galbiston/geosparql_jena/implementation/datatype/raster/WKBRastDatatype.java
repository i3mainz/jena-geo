package io.github.galbiston.geosparql_jena.implementation.datatype.raster;

import java.io.IOException;
import org.apache.sis.coverage.grid.GridCoverage;
import org.geotoolkit.coverage.wkb.WKBRasterReader;
import org.geotoolkit.coverage.wkb.WKBRasterWriter;
import org.opengis.util.FactoryException;

import de.hsmainz.cs.semgis.arqextension.vocabulary.PostGISGeo;
import io.github.galbiston.geosparql_jena.implementation.CoverageWrapper;

public class WKBRastDatatype extends RasterDataType {

	public static final String URI = PostGISGeo.WKBRaster;
	
	public static final WKBRastDatatype INSTANCE = new WKBRastDatatype();
	
	public WKBRastDatatype() {
		super(URI);
	}
	
	@Override
	public String unparse(Object geometry) {
		if (geometry instanceof CoverageWrapper) {
            CoverageWrapper geometryWrapper = (CoverageWrapper) geometry;
            WKBRasterWriter writer=new WKBRasterWriter();
			String rasterWKB;
			try {
				rasterWKB = writer.write(geometryWrapper.getXYGeometry()).toString();
				return rasterWKB.toString();
			} catch (IOException | FactoryException e) {
				throw new AssertionError(e.getMessage());
			}

        } else {
            throw new AssertionError("Object passed to GeoJSONDatatype is not a GeometryWrapper: " + geometry);
        }
	}

	@Override
	public CoverageWrapper read(String geometryLiteral) {
		WKBRasterReader reader2=new WKBRasterReader();
		GridCoverage coverage;
		try {
			coverage = reader2.readCoverage(geometryLiteral.getBytes(), null);
			return new CoverageWrapper(coverage, URI);
		} catch (IOException | FactoryException e) {
			throw new RuntimeException(e.getMessage());
		}

	}
	
    @Override
    public String toString() {
        return "WKBRasterDatatype{" + URI + '}';
    }

}
