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
package de.hsmainz.cs.semgis.arqextension.geometry;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import java.util.ArrayList;
import java.util.List;
import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;

public class DumpPoints extends FunctionBase1 {

    @Override
    public NodeValue exec(NodeValue arg0) {

        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            Geometry geom = geometry.getParsingGeometry();

            List<String> results = new ArrayList<>(geom.getNumPoints());

            String srsURI = geometry.getSrsURI();
            String geometryDatatypeURI = geometry.getGeometryDatatypeURI();
            Coordinate[] coordinates = geom.getCoordinates();
            for (int i = 0; i < geom.getNumPoints(); i++) {

                Coordinate coord = coordinates[i];
                GeometryWrapper resWrapper = GeometryWrapperFactory.createPoint(coord, srsURI, geometryDatatypeURI);
                Literal resLiteral = resWrapper.asLiteral();
                String resString = resLiteral.toString();
                results.add(resString);
            }

            //Returning the list of space delimited literals. This is the same as GROUP_CONCAT.
            //Correct splitting of results for use in query would need a Property Function.
            return NodeValue.makeString(String.join(" ", results));

        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
    }

}
