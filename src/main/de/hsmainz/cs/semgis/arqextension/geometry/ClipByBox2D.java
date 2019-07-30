package de.hsmainz.cs.semgis.arqextension.geometry;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

/**
 * Returns the portion of a geometry falling within a rectangle.
 *
 */
public class ClipByBox2D extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		GeometryWrapper geom1 = GeometryWrapper.extract(v1);
		GeometryWrapper geom2 = GeometryWrapper.extract(v2);
		try {
			return GeometryWrapperFactory.createGeometry(geom1.intersection(geom2).getXYGeometry(),geom1.getSrsURI(),geom1.getGeometryDatatypeURI()).asNodeValue();
		} catch (DatatypeFormatException | MismatchedDimensionException | FactoryException | TransformException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new UnsupportedOperationException("An error occured while clipping");
	}

}
