package de.hsmainz.cs.semgis.arqextension.geometry.constructor;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;

public class MakeCircle extends FunctionBase2{

	@Override
	public NodeValue exec(NodeValue v1, NodeValue v2) {
		MakeEllipse makeel=new MakeEllipse();
		return makeel.exec(v1, v2, v2);
	}

}
