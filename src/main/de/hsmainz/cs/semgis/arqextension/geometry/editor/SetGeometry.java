package de.hsmainz.cs.semgis.arqextension.geometry.editor;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import org.locationtech.jts.geom.Geometry;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

public class SetGeometry extends FunctionBase3 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2,NodeValue v3) {
		GeometryWrapper geometry = GeometryWrapper.extract(v1);
		Geometry geom=geometry.getParsingGeometry();
		GeometryWrapper geometry2 = GeometryWrapper.extract(v2);
		Geometry geom2=geometry2.getParsingGeometry();
		Double position=v3.getDouble();
		List<Geometry> geometries=new LinkedList<Geometry>();
		for(int i=0;i<geom.getNumGeometries();i++) {
			if(i==position) {
				geometries.add(geom2);
			}else {
				geometries.add(geom.getGeometryN(i));
			}
		}
		return GeometryWrapperFactory.createGeometryCollection(geometries, geometry.getSrsURI(), geometry.getGeometryDatatypeURI()).asNodeValue();
	}

}
