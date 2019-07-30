package de.hsmainz.cs.semgis.arqextension.linestring;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Geometry;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

/**
 * Returns a collection containing paths shared by the two input linestrings/multilinestrings.
 *
 */
public class SharedPaths extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue arg0, NodeValue arg1) {
		GeometryWrapper geom1 = GeometryWrapper.extract(arg0);
        GeometryWrapper geom2 = GeometryWrapper.extract(arg1);
        List<Geometry> intersections=new LinkedList<>();
        try {
			GeometryWrapper transGeom2 = geom2.transform(geom1.getSrsInfo());
			if((geom1.getXYGeometry().getGeometryType().equals("MultiLineString") || geom1.getXYGeometry().getGeometryType().equals("LineString"))
					&& (transGeom2.getXYGeometry().getGeometryType().equals("MultiLineString") || transGeom2.getXYGeometry().getGeometryType().equals("LineString"))) {
				for(int i=0;i<geom1.getXYGeometry().getNumGeometries();i++) {
					for(int j=0;j<transGeom2.getXYGeometry().getNumGeometries();j++) {
						if(geom1.getXYGeometry().intersects(transGeom2.getXYGeometry().getGeometryN(j))){
							intersections.add(geom1.getXYGeometry().getGeometryN(i).intersection(transGeom2.getXYGeometry().getGeometryN(j)));
						}
					}
				}
				return GeometryWrapperFactory.createGeometryCollection(intersections, geom1.getSrsURI(), geom1.getGeometryDatatypeURI()).asNodeValue(); 
			}
			throw new ExprEvalException("Given geometries are not LineString or MultiLineString");
		} catch (MismatchedDimensionException | TransformException | FactoryException e) {
			throw new ExprEvalException("Exception occured while transforming CRS");
		}
	}

}
