package io.github.galbiston.geosparql_jena.implementation.datatype.raster;

import java.io.IOException;

import org.apache.jena.sparql.expr.NodeValue;
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.geotoolkit.coverage.io.CoverageIO;
import org.geotoolkit.coverage.io.CoverageStoreException;
import org.geotoolkit.image.io.SpatialImageWriteParam;
import org.geotoolkit.image.io.plugin.TiffImageWriter;

import de.hsmainz.cs.semgis.arqextension.vocabulary.PostGISGeo;
import io.github.galbiston.geosparql_jena.implementation.CoverageWrapper;

public class GeoTIFFDatatype extends RasterDataType {

	public static final String URI = PostGISGeo.GEOTIFF;
	
	public static final GeoTIFFDatatype INSTANCE=new GeoTIFFDatatype();

	public GeoTIFFDatatype() {
		super(URI);
	}

	@Override
	public String unparse(Object geometry) {
		if (geometry instanceof CoverageWrapper) {
			CoverageWrapper geometryWrapper = (CoverageWrapper) geometry;
			TiffImageWriter writer = new TiffImageWriter(null);
			SpatialImageWriteParam writerParam = writer.getDefaultWriteParam();
			String compression = null;
			/*
			 * if (compression != null) {
			 * writerParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			 * writerParam.setCompressionType(compression); }
			 */
			try {
				writer.write(((CoverageWrapper) geometry).getXYGeometry().getRenderedImage());
				writer.endWriteSequence();
				return writer.getOutput().toString();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
		return uri;
	}

	@Override
	public CoverageWrapper read(String geometryLiteral) {
		GridCoverage2D coverage;
		try {
			coverage = CoverageIO.read(geometryLiteral);
			return new CoverageWrapper(coverage, URI);
		} catch (CoverageStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}

	}

}
