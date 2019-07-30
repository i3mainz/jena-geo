package de.hsmainz.cs.semgis.arqextension.raster.exporter;

import java.io.IOException;
import java.util.List;

import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.apache.jena.sparql.function.FunctionEnv;
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.geotoolkit.image.io.SpatialImageWriteParam;
import org.geotoolkit.image.io.plugin.TiffImageWriter;

import io.github.galbiston.geosparql_jena.implementation.CoverageWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; 
public class AsTIFF extends FunctionBase1{

	@Override
	public NodeValue exec(NodeValue v) {
		CoverageWrapper wrapper=CoverageWrapper.extract(v);
		GridCoverage2D raster=wrapper.getXYGeometry();	
		TiffImageWriter writer=new TiffImageWriter(null);
		SpatialImageWriteParam writerParam = writer.getDefaultWriteParam();
		String compression=null;
        /*if (compression != null) {
            writerParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            writerParam.setCompressionType(compression);
        }*/
		try {
			writer.write(raster.getRenderedImage());
			writer.endWriteSequence();
			return NodeValue.makeString(writer.getOutput().toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
