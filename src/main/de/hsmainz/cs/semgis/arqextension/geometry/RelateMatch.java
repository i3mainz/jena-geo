package de.hsmainz.cs.semgis.arqextension.geometry;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;

public class RelateMatch extends FunctionBase2 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		String rel1=v1.getString();
		String rel2=v2.getString();
		return NodeValue.makeBoolean(rel1.equals(rel2));
	}

}
