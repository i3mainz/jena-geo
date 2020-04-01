package de.hsmainz.cs.semgis.arqextension.geometry.srid;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.apache.sis.coverage.grid.GridCoverage;
import org.apache.sis.referencing.CRS;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.util.FactoryException;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import de.hsmainz.cs.semgis.arqextension.util.Wrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

public class SRIDHasFlippedAxis extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
		Wrapper wrapper1=LiteralUtils.rasterOrVector(v);
		CoordinateReferenceSystem crs;
		try {
			if(wrapper1 instanceof GeometryWrapper) {
				crs = CRS.forCode("EPSG:"+((GeometryWrapper)wrapper1).getXYGeometry().getSRID());
				if("Y".equals(crs.getCoordinateSystem().getAxis(0).getName().toString()) && "X".equals(crs.getCoordinateSystem().getAxis(1).getName().toString())){
					return NodeValue.TRUE;
				}else {
					return NodeValue.FALSE;
				}
			}else if(wrapper1 instanceof CoverageWrapper) {
				GridCoverage raster=((CoverageWrapper)wrapper1).getXYGeometry();
				crs = CRS.forCode("EPSG:"+raster.getGridGeometry().CRS);
				if("Y".equals(crs.getCoordinateSystem().getAxis(0).getName().toString()) && "X".equals(crs.getCoordinateSystem().getAxis(1).getName().toString())){
					return NodeValue.TRUE;
				}else {
					return NodeValue.FALSE;
				}			}
		} catch (FactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return NodeValue.FALSE;
	}

}
