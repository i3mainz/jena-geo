package de.hsmainz.cs.semgis.arqextension.raster;

import java.awt.geom.Rectangle2D;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.geotoolkit.coverage.grid.GridCoverage2D;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LineString;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import de.hsmainz.cs.semgis.arqextension.util.Wrapper;
import io.github.galbiston.geosparql_jena.implementation.CoverageWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;

public class ContainsProperly extends FunctionBase2 {

	
	//TODO: Convert to properly contains
	
	@Override
	public NodeValue exec(NodeValue v,NodeValue v1) {
		Wrapper wrapper1=LiteralUtils.rasterOrVector(v);
		Wrapper wrapper2=LiteralUtils.rasterOrVector(v1);
		if(wrapper1 instanceof GeometryWrapper && wrapper2 instanceof GeometryWrapper) {
			return NodeValue.makeBoolean(containsProperly(((GeometryWrapper)wrapper1).getXYGeometry(), ((GeometryWrapper)wrapper2).getXYGeometry()));
		}else if(wrapper1 instanceof CoverageWrapper && wrapper2 instanceof CoverageWrapper) {
			GridCoverage2D raster=((CoverageWrapper)wrapper1).getXYGeometry();
			GridCoverage2D raster2=((CoverageWrapper)wrapper2).getXYGeometry();		
	        Rectangle2D bbox1 = raster.getEnvelope2D().getBounds2D();
	        Rectangle2D bbox2 = raster2.getEnvelope2D().getBounds2D();
	        Geometry bboxgeom1=LiteralUtils.toGeometry(bbox1.getBounds());
	        Geometry bboxgeom2=LiteralUtils.toGeometry(bbox2.getBounds());
	        return NodeValue.makeBoolean(containsProperly(bboxgeom1, bboxgeom2));
		}else {
			if(wrapper1 instanceof CoverageWrapper) {
				GridCoverage2D raster=((CoverageWrapper)wrapper1).getXYGeometry();
				Rectangle2D bbox1 = raster.getEnvelope2D().getBounds2D();
				Geometry bboxgeom1=LiteralUtils.toGeometry(bbox1.getBounds());
				Geometry geom=((GeometryWrapper)wrapper2).getXYGeometry();
				return NodeValue.makeBoolean(containsProperly(bboxgeom1, geom));
			}else {
				GridCoverage2D raster=((CoverageWrapper)wrapper2).getXYGeometry();
				Rectangle2D bbox1 = raster.getEnvelope2D().getBounds2D();
				Geometry bboxgeom1=LiteralUtils.toGeometry(bbox1.getBounds());
				Geometry geom=((GeometryWrapper)wrapper1).getXYGeometry();
				return NodeValue.makeBoolean(containsProperly(bboxgeom1, geom));				
			}
		}
	}
	
	
	public Boolean containsProperly(Geometry geom1,Geometry geom2) {
        if(!geom1.contains(geom2)) {
        	return false;
        }else {
        	Coordinate oldcoord=null;
        	for(Coordinate coord:geom1.getCoordinates()) {
        		if(oldcoord!=null) {
        			LineString ls=(LineString) GeometryWrapperFactory.createLineString(new Coordinate[] {oldcoord, coord},WKTDatatype.URI).getXYGeometry();
        			if(ls.intersects(geom2)) {
        				return false;
        			}
        		}
        		oldcoord=coord;
        	}
        	return true;
        }
	}

}
