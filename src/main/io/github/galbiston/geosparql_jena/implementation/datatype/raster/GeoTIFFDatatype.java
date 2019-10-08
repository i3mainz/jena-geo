package io.github.galbiston.geosparql_jena.implementation.datatype.raster;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.sis.coverage.grid.GridCoverage;
import org.apache.sis.coverage.grid.GridGeometry;
import org.apache.sis.storage.DataStoreException;
import org.apache.sis.storage.StorageConnector;
import org.apache.sis.storage.geotiff.GeoTiffStore;
import org.apache.sis.storage.geotiff.GeoTiffStoreProvider;
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
				writer.write(((CoverageWrapper) geometry).getXYGeometry().render(null).getData());
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
		InputStream stream = new ByteArrayInputStream(geometryLiteral.getBytes(StandardCharsets.UTF_8));
		final StorageConnector c = new StorageConnector(stream);
		GeoTiffStoreProvider prov=new GeoTiffStoreProvider();
		GridCoverage cov;
		try {
			GeoTiffStore store=new GeoTiffStore(prov,c);
			cov = store.components().get(0).read(store.components().get(0).getGridGeometry(), 1);
			return new CoverageWrapper(cov, URI);
		} catch (DataStoreException e) {
			throw new RuntimeException(e.getMessage());
		}

	}

}
