package de.hsmainz.cs.semgis.arqextension.raster.editor;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import org.apache.sis.coverage.grid.GridCoverage;
import org.apache.sis.coverage.grid.GridCoverageBuilder;
import org.apache.sis.internal.coverage.j2d.BufferedGridCoverage;

import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

public class SetSkew extends FunctionBase3 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2, NodeValue v3) {
		CoverageWrapper wrapper=CoverageWrapper.extract(v1);
		GridCoverage raster=wrapper.getXYGeometry();
		Double scalex=v2.getDouble();
		Double scaley=v3.getDouble();
		GridCoverageBuilder builder;
		builder.setDomain(raster.getGridGeometry());
		builder.setRanges(raster.getSampleDimensions());
		builder.setValues(raster.render(null).getData().getDataBuffer());
		Writable
		WritableGridCoverage cov=new BufferedGridCoverage(raster.getGridGeometry(), raster.getSampleDimensions(), raster.render(null).getData().getDataBuffer());
		cov.
		return null;
	}

}
