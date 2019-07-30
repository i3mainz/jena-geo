package de.hsmainz.cs.semgis.arqextension.raster;

import java.util.List;

import org.apache.jena.sparql.expr.ExprList;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;

/**
 * Returns a set of rasters resulting from the split of the input raster based upon the desired dimensions of the output rasters. 
 *
 */
public class Tile extends FunctionBase {

	@Override
	public NodeValue exec(List<NodeValue> args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void checkBuild(String uri, ExprList args) {
		// TODO Auto-generated method stub
		
	}

}
