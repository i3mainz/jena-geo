package de.hsmainz.cs.semgis.arqextension.geometry;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.geolatte.geom.Position;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

public class LongestLine3D extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue arg0, NodeValue arg1) {
        GeometryWrapper geom1 = GeometryWrapper.extract(arg0);
        GeometryWrapper geom2 = GeometryWrapper.extract(arg1);
        try {
			GeometryWrapper transGeom2 = geom2.transform(geom1.getSRID());
        } catch (MismatchedDimensionException | TransformException | FactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
