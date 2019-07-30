package de.hsmainz.cs.semgis.arqextension.geometry;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Geometry;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;
import io.github.galbiston.geosparql_jena.implementation.datatype.WKTDatatype;
import io.github.galbiston.geosparql_jena.implementation.vocabulary.SRS_URI;

public class PointOnSurface extends FunctionBase1{

	@Override
	public NodeValue exec(NodeValue arg0) {
       GeometryWrapper geometry = GeometryWrapper.extract(arg0);
       Geometry geom = geometry.getXYGeometry();
       GeometryWrapper pointWrapper = GeometryWrapperFactory.createPoint(geom.getInteriorPoint().getCoordinate(), SRS_URI.DEFAULT_WKT_CRS84, WKTDatatype.URI);
       return pointWrapper.asNodeValue();
	}

}
