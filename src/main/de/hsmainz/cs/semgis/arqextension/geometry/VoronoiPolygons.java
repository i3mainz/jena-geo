package de.hsmainz.cs.semgis.arqextension.geometry;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper; import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

public class VoronoiPolygons extends FunctionBase3 {

	@Override
	public NodeValue exec(NodeValue v1, NodeValue toleranceV, NodeValue extendto) {
		
        try {
            GeometryWrapper geom1 = GeometryWrapper.extract(v1);
            Double tolerance=toleranceV.getDouble();
    		VoronoiDiagramBuilder builder=new VoronoiDiagramBuilder();
    		builder.setTolerance(tolerance);
    		builder.setSites(geom1.getXYGeometry());
    		Geometry test = builder.getDiagram(new GeometryFactory());
            
            GeometryWrapper pointWrapper = GeometryWrapperFactory.createGeometry(test, geom1.getSrsURI(), geom1.getGeometryDatatypeURI());

            return pointWrapper.asNodeValue();
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }

	}

}
