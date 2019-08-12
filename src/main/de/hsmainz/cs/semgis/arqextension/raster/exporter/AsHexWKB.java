package de.hsmainz.cs.semgis.arqextension.raster.exporter;

import java.io.IOException;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.apache.sis.coverage.grid.GridCoverage;
import org.geotoolkit.coverage.wkb.WKBRasterWriter;
import org.locationtech.jts.io.WKBWriter;
import org.opengis.util.FactoryException;

import io.github.galbiston.geosparql_jena.implementation.CoverageWrapper;

public class AsHexWKB extends FunctionBase1{
	

	@Override
	public NodeValue exec(NodeValue v) {
		CoverageWrapper wrapper=CoverageWrapper.extract(v);
		GridCoverage raster=wrapper.getXYGeometry();	
		WKBRasterWriter writer=new WKBRasterWriter();
		String rasterWKB;
		try {
			rasterWKB = WKBWriter.toHex(writer.write(raster)).toString();
			return NodeValue.makeString(rasterWKB.toString());
		} catch (IOException | FactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
