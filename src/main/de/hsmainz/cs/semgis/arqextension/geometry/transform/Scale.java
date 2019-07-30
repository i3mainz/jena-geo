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
package de.hsmainz.cs.semgis.arqextension.geometry.transform;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; 
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

import java.util.List;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.ExprList;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.util.AffineTransformation;

public class Scale extends FunctionBase {

    /**
     * 
     */
	@Override
	public NodeValue exec(List<NodeValue> args) {
        try {
            GeometryWrapper geometry = GeometryWrapper.extract(args.get(0));
            Geometry geom = geometry.getXYGeometry();
            Geometry originPoint=null;
            Double xFactor = 1.;
            Double yFactor = 1.;
            Double zFactor=1.;
            if(args.size()<2) {
            	 return GeometryWrapperFactory.createGeometry(geom, geometry.getSrsURI(), geometry.getGeometryDatatypeURI()).asNodeValue();
            }else if(args.size()>=2) {
            	if(args.size()>=2 && args.get(1).isDouble()) {
            		xFactor=args.get(1).getDouble();
            	}else  {
                    GeometryWrapper matchPoint = GeometryWrapper.extract(args.get(1));
                    Geometry mPoint = matchPoint.getXYGeometry();
                    xFactor=mPoint.getCoordinate().x;
                    yFactor=mPoint.getCoordinate().y;
                    zFactor=mPoint.getCoordinate().z!=Double.NaN?mPoint.getCoordinate().z:1.;
            	}
            	if(args.size()>=3 && args.get(2).isDouble()) {
            		yFactor=args.get(2).getDouble();
            	}else {
            		GeometryWrapper oriPoint = GeometryWrapper.extract(args.get(1));
                    originPoint = oriPoint.getXYGeometry();
                    
            	}
            	if(args.size()>=4 && args.get(3).isDouble()) {
            		zFactor=args.get(3).getDouble();
            	}
            }
            AffineTransformation trans=new AffineTransformation();
            if(originPoint==null) {
            	trans.scale(xFactor, yFactor);	
            }else {
                trans.translate(-originPoint.getCoordinate().x, -originPoint.getCoordinate().y);
                trans.scale(xFactor, yFactor);
                trans.translate(originPoint.getCoordinate().x, originPoint.getCoordinate().y);
            }
            return GeometryWrapperFactory.createGeometry(trans.transform(geom), geometry.getSrsURI(), geometry.getGeometryDatatypeURI()).asNodeValue();
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
	}

	@Override
	public void checkBuild(String uri, ExprList args) {
		// TODO Auto-generated method stub
		
	}

}
