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

import java.util.List;
import org.apache.jena.atlas.lib.Lib;
import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.query.QueryBuildException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.ExprList;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.util.AffineTransformation;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

public class Affine extends FunctionBase {

    @Override
    public NodeValue exec(List<NodeValue> args) {
		try {
	        GeometryWrapper geometry = GeometryWrapper.extract(args.get(0));
	        Geometry geom = geometry.getXYGeometry();
	        /*Double xShear=v1.getDouble();
	        Double yShear=v2.getDouble();
	        AffineTransformation trans = new AffineTransformation();
	        trans.setTransformation(m00, m01, m02, m10, m11, m12)
	        trans.setToShear(xShear, yShear);*/
	        return GeometryWrapperFactory.createGeometry(trans.transform(geom), geometry.getSrsURI(), geometry.getGeometryDatatypeURI()).asNodeValue();
	    } catch (DatatypeFormatException ex) {
	        throw new ExprEvalException(ex.getMessage(), ex);
	    }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void checkBuild(String uri, ExprList args) {
        if (args.size() != 7 || args.size() != 13) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' takes seven or thirteen arguments");
        }
    }

}
