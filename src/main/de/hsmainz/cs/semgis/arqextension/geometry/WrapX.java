package de.hsmainz.cs.semgis.arqextension.geometry;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import org.locationtech.jts.geom.Geometry;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

/**
 * Wrap a geometry around an X value.
 *
 */
public class WrapX extends FunctionBase3 {

	@Override
	public NodeValue exec(NodeValue arg0, NodeValue arg1, NodeValue arg2) {
        GeometryWrapper geometry = GeometryWrapper.extract(arg0);
        Geometry geom = geometry.getXYGeometry();
        Double wrap=arg1.getDouble();
        Double move=arg2.getDouble();
        throw new UnsupportedOperationException("Not supported yet.");
	}

}
