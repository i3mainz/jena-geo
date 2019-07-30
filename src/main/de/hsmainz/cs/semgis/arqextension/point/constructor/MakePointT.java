package de.hsmainz.cs.semgis.arqextension.point.constructor;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateXYM;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;
import io.github.galbiston.geosparql_jena.implementation.vocabulary.SRS_URI;

public class MakePointT extends FunctionBase3 {

    @Override
    public NodeValue exec(NodeValue arg0, NodeValue arg1, NodeValue arg2) {
    	//arg2.getD
        Coordinate coord = new CoordinateXYM(arg0.getDouble(), arg1.getDouble(), arg2.getDouble());
        GeometryWrapper pointWrapper = GeometryWrapperFactory.createPoint(coord, SRS_URI.DEFAULT_WKT_CRS84, WKTDatatype.URI);
        return pointWrapper.asNodeValue();
    }

}
