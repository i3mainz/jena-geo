/** *****************************************************************************
 * Copyright (c) 2017 Timo Homburg, i3Mainz.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the BSD License
 * which accompanies this distribution, and is available at
 * https://directory.fsf.org/wiki/License:BSD_4Clause
 *
 * This project extends work by Ian Simmons who developed the Parliament Triple Store.
 * http://parliament.semwebcentral.org and published his work und BSD License as well.
 *
 *
 ****************************************************************************** */
package de.hsmainz.cs.semgis.arqextension.raster.relation;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

import java.awt.geom.Rectangle2D;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.operation.distance.DistanceOp;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import de.hsmainz.cs.semgis.arqextension.util.Wrapper;

public class DWithin extends FunctionBase3 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2,NodeValue v3) {
		Wrapper wrapper1=LiteralUtils.rasterOrVector(v1);
		Wrapper wrapper2=LiteralUtils.rasterOrVector(v2);
        Double withinDistance = v3.getDouble();
        /*GeodeticCalculator calc = new GeodeticCalculator();
		if(wrapper1 instanceof GeometryWrapper && wrapper2 instanceof GeometryWrapper) {
			Double actualDistance=((GeometryWrapper)wrapper1).getXYGeometry().distance(((GeometryWrapper)wrapper2).getXYGeometry());
			return NodeValue.makeBoolean(actualDistance<=withinDistance);
		}else if(wrapper1 instanceof CoverageWrapper && wrapper2 instanceof CoverageWrapper) {
			GridCoverage raster=((CoverageWrapper)wrapper1).getGridGeometry();
			GridCoverage raster2=((CoverageWrapper)wrapper2).getGridGeometry();	
			Geometry bbox1 = LiteralUtils.toGeometry(raster.getGridGeometry().getEnvelope());
		    Geometry bbox2 = LiteralUtils.toGeometry(raster2.getGridGeometry().getEnvelope());
			Coordinate[] points = DistanceOp.nearestPoints(bbox1,bbox2);
	        calc.setStartingGeographicPoint(points[0].x, points[0].y);
	        calc.setDestinationGeographicPoint(points[1].x, points[1].y);
	        Double actualdistance = calc.getOrthodromicDistance();
			return NodeValue.makeBoolean(actualdistance<=withinDistance);		
		}else {
			if(wrapper1 instanceof CoverageWrapper) {
				GridCoverage raster=((CoverageWrapper)wrapper1).getGridGeometry();
				Rectangle2D bbox1 = raster.getGridGeometry().getEnvelope().getBounds();
				Geometry geom=((GeometryWrapper)wrapper2).getXYGeometry();
				Coordinate[] points = DistanceOp.nearestPoints(LiteralUtils.toGeometry(bbox1.getBounds()), geom);
		        calc.setStartingGeographicPoint(points[0].x, points[0].y);
		        calc.setDestinationGeographicPoint(points[1].x, points[1].y);
		        Double actualdistance = calc.getOrthodromicDistance();
				return NodeValue.makeBoolean(actualdistance<=withinDistance);
			}else {
				GridCoverage raster=((CoverageWrapper)wrapper2).getGridGeometry();
				Rectangle2D bbox1 = raster.getEnvelope2D().getBounds2D();
				Geometry geom=((GeometryWrapper)wrapper1).getXYGeometry();
				Coordinate[] points = DistanceOp.nearestPoints(LiteralUtils.toGeometry(bbox1.getBounds()), geom);
		        calc.setStartingGeographicPoint(points[0].x, points[0].y);
		        calc.setDestinationGeographicPoint(points[1].x, points[1].y);
		        Double actualdistance = calc.getOrthodromicDistance();
				return NodeValue.makeBoolean(actualdistance<=withinDistance);				
			}
		}*/
		throw new UnsupportedOperationException("Not yet implemented");
	}

}
