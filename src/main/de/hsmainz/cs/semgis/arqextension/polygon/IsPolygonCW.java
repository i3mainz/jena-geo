package de.hsmainz.cs.semgis.arqextension.polygon;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;

/**
 * Returns true if all exterior rings are oriented clockwise and all interior rings are oriented counter-clockwise. 
 *
 */
public class IsPolygonCW extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
        return null;
	}
	
}
