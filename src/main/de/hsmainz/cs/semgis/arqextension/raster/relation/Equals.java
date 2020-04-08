package de.hsmainz.cs.semgis.arqextension.raster.relation;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.apache.sis.coverage.grid.GridCoverage;
import org.locationtech.jts.geom.Geometry;
import org.opengis.geometry.Envelope;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import de.hsmainz.cs.semgis.arqextension.util.Wrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

public class Equals extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		Wrapper wrapper1=LiteralUtils.rasterOrVector(v1);
		Wrapper wrapper2=LiteralUtils.rasterOrVector(v2);
		if(wrapper1 instanceof GeometryWrapper && wrapper2 instanceof GeometryWrapper) {
			GeometryWrapper transGeom2;
			try {
				transGeom2 = ((GeometryWrapper)wrapper2).transform(((GeometryWrapper)wrapper1).getSrsInfo());
				return NodeValue.makeBoolean(((GeometryWrapper)wrapper1).getXYGeometry().equals(transGeom2.getXYGeometry()));
			} catch (MismatchedDimensionException | TransformException | FactoryException e) {
				throw new RuntimeException("CRS transformation failed");
			}
		}else if(wrapper1 instanceof CoverageWrapper && wrapper2 instanceof CoverageWrapper) {
			GridCoverage raster=((CoverageWrapper)wrapper1).getGridGeometry();
			GridCoverage raster2=((CoverageWrapper)wrapper2).getGridGeometry();	
			return NodeValue.makeBoolean(raster.equals(raster2));		
		}else {
			if(wrapper1 instanceof CoverageWrapper) {
				GridCoverage raster=((CoverageWrapper)wrapper1).getGridGeometry();
				Envelope bbox1 = raster.getGridGeometry().getEnvelope();
				Geometry geom=((GeometryWrapper)wrapper2).getXYGeometry();
				return NodeValue.makeBoolean(LiteralUtils.toGeometry(bbox1).equals(geom));
			}else {
				GridCoverage raster=((CoverageWrapper)wrapper2).getGridGeometry();
				Envelope bbox1 = raster.getGridGeometry().getEnvelope();
				Geometry geom=((GeometryWrapper)wrapper1).getXYGeometry();
				return NodeValue.makeBoolean(geom.equals(LiteralUtils.toGeometry(bbox1)));				
			}
		}

	}

}
