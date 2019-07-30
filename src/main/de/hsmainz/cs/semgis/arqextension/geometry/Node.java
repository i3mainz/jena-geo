package de.hsmainz.cs.semgis.arqextension.geometry;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.algorithm.RobustLineIntersector;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.noding.IntersectionAdder;
import org.locationtech.jts.noding.MCIndexNoder;
import org.locationtech.jts.noding.Noder;
import org.locationtech.jts.noding.SegmentStringUtil;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

public class Node extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
		GeometryWrapper geom1 = GeometryWrapper.extract(v);
		 Noder noder = new MCIndexNoder(new IntersectionAdder(new RobustLineIntersector()));
	        noder.computeNodes(SegmentStringUtil.extractNodedSegmentStrings(geom1.getXYGeometry()));
	        GeometryFactory fac=new GeometryFactory();
	        return GeometryWrapperFactory.createGeometry(SegmentStringUtil.toGeometry(noder.getNodedSubstrings(), fac),geom1.getSrsURI(),geom1.getGeometryDatatypeURI()).asNodeValue();
	}

}
