package de.hsmainz.cs.semgis.arqextension.geometry.attribute;

import java.util.HashSet;
import java.util.Set;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Geometry;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class NumDistinctGeometries extends FunctionBase1 {

    @Override
    public NodeValue exec(NodeValue arg0) {

        try {
            GeometryWrapper geometry = GeometryWrapper.extract(arg0);
            Geometry geom = geometry.getParsingGeometry();
            Set<Geometry> geoms=new HashSet<Geometry>();
            for(int i=0;i<geom.getNumGeometries();i++) {
            	geoms.add(geom.getGeometryN(i));
            }
            return NodeValue.makeInteger(geoms.size());
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
    }

}
