package de.hsmainz.cs.semgis.arqextension.geometry.srid;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.apache.sis.coverage.grid.GridCoverage;
import org.apache.sis.referencing.CRS;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.util.FactoryException;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import de.hsmainz.cs.semgis.arqextension.util.Wrapper;
import io.github.galbiston.geosparql_jena.implementation.CoverageWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class SRIDGetAxis1Name extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
		Wrapper wrapper1=LiteralUtils.rasterOrVector(v);
		CoordinateReferenceSystem crs;
		try {
			if(wrapper1 instanceof GeometryWrapper) {
				crs = CRS.forCode("EPSG:"+((GeometryWrapper)wrapper1).getXYGeometry().getSRID());
				return NodeValue.makeString(crs.getCoordinateSystem().getAxis(0).getName().toString());
			}else if(wrapper1 instanceof CoverageWrapper) {
				GridCoverage raster=((CoverageWrapper)wrapper1).getXYGeometry();
				crs = CRS.forCode("EPSG:"+raster.getGridGeometry().CRS);
				return NodeValue.makeString(crs.getCoordinateSystem().getAxis(0).getName().toString());		
			}
		} catch (FactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return NodeValue.nvEmptyString;
	}

}
