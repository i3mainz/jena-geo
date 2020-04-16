package de.hsmainz.cs.semgis.arqextension.math;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;

public class ToDegrees extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v1) {
		Double value=v1.getDouble();
		return NodeValue.makeDouble(Math.toDegrees(value));	       
	}

}
