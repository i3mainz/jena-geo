package de.hsmainz.cs.semgis.arqextension.geometry.editor;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import org.locationtech.jts.geom.Geometry;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

public class RemoveGeometry extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		GeometryWrapper geometry = GeometryWrapper.extract(v1);
		Geometry geom=geometry.getParsingGeometry();
		Double geomindex=v2.getDouble();
		List<Geometry> geometries=new LinkedList<Geometry>();
		for(int i=0;i<geom.getNumGeometries();i++) {
			if(i!=geomindex) {
				geometries.add(geom.getGeometryN(i));
			}
		}
		return GeometryWrapperFactory.createGeometryCollection(geometries, geometry.getSrsURI(), geometry.getGeometryDatatypeURI()).asNodeValue();
	}

}
