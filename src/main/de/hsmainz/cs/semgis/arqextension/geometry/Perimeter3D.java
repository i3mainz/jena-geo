package de.hsmainz.cs.semgis.arqextension.geometry;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Polygon;

import de.hsmainz.cs.semgis.arqextension.linestring.LineLength3D;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;

public class Perimeter3D extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue v) {
		try {
            GeometryWrapper geometry = GeometryWrapper.extract(v);
            Geometry geom = geometry.getXYGeometry();
            double sum = 0;
            for (int i = 0; i < geom.getNumGeometries(); i++) {
                Geometry subGeom = geom.getGeometryN(i);
                if (subGeom instanceof Polygon) {
                    sum += LineLength3D.length3D(((Polygon) subGeom).getExteriorRing());
                } 
            }
            return NodeValue.makeDouble(sum);
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        }
		
	}

}
