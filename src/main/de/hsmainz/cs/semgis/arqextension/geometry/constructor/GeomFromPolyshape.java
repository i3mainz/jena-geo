package de.hsmainz.cs.semgis.arqextension.geometry.constructor;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.datatype.geometry.PolyshapeDatatype;

public class GeomFromPolyshape extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
		GeometryWrapper geom=PolyshapeDatatype.INSTANCE.read(v.getString());
        return geom.asNodeValue();  
	}

}
