package de.hsmainz.cs.semgis.arqextension.geometry;

import java.util.LinkedList;
import java.util.List;

import org.apache.jena.sparql.expr.ExprList;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;
import org.locationtech.jts.geom.Geometry;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

public class Accum extends FunctionBase {

	@Override
	public NodeValue exec(List<NodeValue> args) {
		List<Geometry> geoms=new LinkedList<Geometry>();
		GeometryWrapper first = GeometryWrapper.extract(args.get(0));
		for(NodeValue nv:args) {
			GeometryWrapper geometry = GeometryWrapper.extract(nv);
			geoms.add(geometry.getParsingGeometry());
		}
		return GeometryWrapperFactory.createGeometryCollection(geoms, first.getSrsURI(), first.getGeometryDatatypeURI()).asNodeValue();
	}

	@Override
	public void checkBuild(String uri, ExprList args) {
		// TODO Auto-generated method stub
		
	}

}
