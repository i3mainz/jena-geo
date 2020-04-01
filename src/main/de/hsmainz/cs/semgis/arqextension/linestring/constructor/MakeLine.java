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
package de.hsmainz.cs.semgis.arqextension.linestring.constructor;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.SRSInfo;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;
import java.util.List;
import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.ExprList;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.operation.linemerge.LineMerger;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

/**
 * Creates a Linestring from point, multipoint, or line geometries.
 *
 */
public class MakeLine extends FunctionBase {

    @Override
    public NodeValue exec(List<NodeValue> args) {

        try {
            SRSInfo srsInfo = SRSInfo.DEFAULT_WKT_CRS84;
            LineMerger linemerge = new LineMerger();

            for (NodeValue arg : args) {
                //Convert the supplied geometries to the output SRS.
                GeometryWrapper geometry = GeometryWrapper.extract(arg);
                GeometryWrapper transGeometry = geometry.transform(srsInfo);
                Geometry geom = transGeometry.getXYGeometry();

                linemerge.add(geom);
            }

            Geometry lineString = (Geometry) linemerge.getMergedLineStrings().iterator().next();

            GeometryWrapper lineStringWrapper = GeometryWrapperFactory.createGeometry(lineString, srsInfo.getSrsURI(), WKTDatatype.URI);

            return lineStringWrapper.asNodeValue();
        } catch (DatatypeFormatException | FactoryException | TransformException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
    }

    @Override
    public void checkBuild(String arg0, ExprList arg1) {
    }

}
