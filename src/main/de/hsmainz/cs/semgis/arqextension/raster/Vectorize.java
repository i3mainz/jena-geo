package de.hsmainz.cs.semgis.arqextension.raster;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.apache.jena.sparql.function.FunctionEnv;
import org.apache.sis.measure.Range;
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.geotoolkit.data.FeatureIterator;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKTWriter;
import org.opengis.feature.Feature;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

public class Vectorize extends FunctionBase1 {

    //static private PolygonExtractionProcess extractor = new PolygonExtractionProcess();

    static private WKTWriter writer =  new WKTWriter();





    public static void main(String[] args) {

        String image = "ncols         4\n" + "nrows         6\n" +
                "xllcorner     0.0\n" + "yllcorner     0.0\n" +
                "cellsize      50.0\n" + "NODATA_value  -9999\n" +
                "-9999 -9999 5 2\n" + "-9999 20 100 36\n" + "3 8 35 10\n" +
                "32 42 50 6\n" + "88 75 27 9\n" + "13 5 1 -9999";

        GridCoverage2D asciiGridFromstring = null;
        try {
            asciiGridFromstring = getAsciiGridFromstring(image);
            String result = getWktForDbRangeFromRaster(asciiGridFromstring, 10);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("complete");

    }

    public static GridCoverage2D getAsciiGridFromstring(String inputAsciiGrid) throws IOException {
        InputStream stringAsStream = new ByteArrayInputStream(inputAsciiGrid.getBytes(StandardCharsets.UTF_8));
        return new ArcGridReader(stringAsStream).read(null);
    }

    public static String getWktForDbRangeFromRaster(GridCoverage2D input, Integer value) throws Exception {
        Range range = Range.create(value, true, 200, false);
        List<Range> classificationRanges = Arrays.asList(range);

        // TODO optimization: can we initialize an Array of fixed length?
        FeatureIterator vectorizedFeatures = extractor.execute(input, 0, true, null,
                null, classificationRanges, null).features();

        Boolean firstElement = true;
        String result = null;
        while (vectorizedFeatures.hasNext()) {
            Feature vectorizedFeature = vectorizedFeatures.next();
            result = writer.write((Geometry) vectorizedFeature.getDefaultGeometry());
            if (firstElement) {
                firstElement = false;
            } else {
                System.out.println("##################################");
                System.out.println("error, can only output single geometry");
                System.out.println(result);
                System.out.println("##################################");
                throw new Exception("only single element is allowed");
            }
        }
        return result;
    }

	@Override
	public NodeValue exec(NodeValue v) {
		// TODO Auto-generated method stub
		return null;
	}

}
