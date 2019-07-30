package de.hsmainz.cs.semgis.arqextension.raster;

import java.awt.image.DataBuffer;
import java.awt.image.WritableRaster;
import java.util.List;

import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase0;
import org.apache.jena.sparql.function.FunctionEnv;
import org.apache.sis.geometry.Envelope2D;
import org.apache.sis.referencing.CommonCRS;
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.geotoolkit.coverage.grid.GridCoverageBuilder;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

public class MakeEmptyCoverage extends FunctionBase0 {

	@Override
	public NodeValue exec() {
		Integer width;
        Integer height;
        Double upperleftx;
        Double upperlefty;
        Integer tilewidth;
        Integer tileheight;
        double scalex;
        double scaley;
        double skewx;
        double skewy;
        Integer srid;
        GridCoverageBuilder factory = new GridCoverageBuilder();
        if (rasterr != null) {
            //factory.
        } else {
            width = evalArgs.get(0).getInteger().intValue();
            height = evalArgs.get(1).getInteger().intValue();
            upperleftx = evalArgs.get(2).getDouble();
            upperlefty = evalArgs.get(3).getDouble();
        }
        
        WritableRaster raster = RasterFactory.createBandedRaster(DataBuffer.TYPE_FLOAT,
                width, height, 1, null);
for (int y=0; y<height; y++) {
for (int x=0; x<width; x++) {
raster.setSample(x, y, 0, x+y);
}
}
        
        CoordinateReferenceSystem crs = CommonCRS.WGS84.normalizedGeographic();
        Envelope2D envelope = new Envelope2D(crs, 0, 0, 30, 30);

        GridCoverageBuilder gcb = new GridCoverageBuilder();
        gcb.setName("My grayscale coverage");
        gcb.setRenderedImage(raster);
        gcb.setEnvelope(envelope);
        GridCoverage2D gc = gcb.getGridCoverage2D();
        return null;
	}

}
