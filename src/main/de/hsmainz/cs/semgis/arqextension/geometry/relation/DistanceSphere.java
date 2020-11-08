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
package de.hsmainz.cs.semgis.arqextension.geometry.relation;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Geometry;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

import de.hsmainz.cs.semgis.arqextension.util.LiteralUtils;
import de.hsmainz.cs.semgis.arqextension.util.Wrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

public class DistanceSphere extends FunctionBase2 {

    @Override
    public NodeValue exec(NodeValue arg0, NodeValue arg1) {
		Wrapper wrapper1=LiteralUtils.rasterOrVector(arg0);
		Wrapper wrapper2=LiteralUtils.rasterOrVector(arg1);
    	if(wrapper1 instanceof GeometryWrapper) {
        try {
            GeometryWrapper geom1 = GeometryWrapper.extract(arg0);
            GeometryWrapper geom2 = GeometryWrapper.extract(arg1);
            GeometryWrapper transGeom2 = geom2.transform(geom1.getSrsInfo());
            double distance = geom1.distanceGreatCircle(transGeom2);

            return NodeValue.makeDouble(distance);
        } catch (DatatypeFormatException | FactoryException | MismatchedDimensionException | TransformException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
    	}
    	return null;

    }

}
