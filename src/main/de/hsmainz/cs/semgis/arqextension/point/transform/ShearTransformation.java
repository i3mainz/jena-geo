package de.hsmainz.cs.semgis.arqextension.point;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.util.AffineTransformation;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

public class ShearTransformation extends FunctionBase3{

	@Override
	public NodeValue exec(NodeValue v, NodeValue v1, NodeValue v2) {
		try {
        GeometryWrapper geometry = GeometryWrapper.extract(v);
        Geometry geom = geometry.getXYGeometry();
        Double xShear=v1.getDouble();
        Double yShear=v2.getDouble();
        AffineTransformation trans = new AffineTransformation();
        trans.setToShear(xShear, yShear);
        return GeometryWrapperFactory.createGeometry(trans.transform(geom), geometry.getSrsURI(), geometry.getGeometryDatatypeURI()).asNodeValue();
    } catch (DatatypeFormatException ex) {
        throw new ExprEvalException(ex.getMessage(), ex);
    }
        
	}

}
