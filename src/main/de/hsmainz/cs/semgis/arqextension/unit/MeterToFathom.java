package de.hsmainz.cs.semgis.arqextension.unit;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;

public class MeterToFathom extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
		Double value=Double.valueOf(v.getDouble());
		return NodeValue.makeDouble(value*0.5468066492);
	} 

}
