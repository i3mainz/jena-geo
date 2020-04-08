package de.hsmainz.cs.semgis.arqextension.raster.editor;

import java.math.BigInteger;
import java.util.List;

import org.apache.jena.sparql.expr.ExprList;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;
import org.apache.sis.coverage.grid.GridCoverage;

import io.github.galbiston.geosparql_jena.implementation.datatype.raster.CoverageWrapper;

public class SetBandNoDataValue extends FunctionBase {

	@Override
	public NodeValue exec(List<NodeValue> args) {
		if(args.size()==2) {
			CoverageWrapper wrapper=CoverageWrapper.extract(args.get(0));
			GridCoverage raster=wrapper.getParsingGeometry();
			BigInteger bandnum=args.get(1).getInteger();
			Double nodataval=args.get(2).getDouble();
			if(raster.getSampleDimensions().get(1).getNoDataValues().contains(nodataval)) {
				return wrapper.asNodeValue();
			}
		}else if(args.size()==3) {
			CoverageWrapper wrapper=CoverageWrapper.extract(args.get(0));
			GridCoverage raster=wrapper.getParsingGeometry();
			BigInteger bandnum=args.get(1).getInteger();
			Double nodataval=args.get(2).getDouble();
			if(raster.getSampleDimensions().get(bandnum.intValue()).getNoDataValues().contains(nodataval)) {
				return wrapper.asNodeValue();
			}else {
				return null;
			}
		}else {
			throw new UnsupportedOperationException("Not supported yet.");
		}
		return null;
	}

	@Override
	public void checkBuild(String uri, ExprList args) {
		// TODO Auto-generated method stub
		
	}

}
