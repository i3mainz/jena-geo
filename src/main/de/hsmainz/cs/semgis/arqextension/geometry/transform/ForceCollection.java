package de.hsmainz.cs.semgis.arqextension.geometry.transform;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapperFactory;

/**
 * Convert the geometry into a GEOMETRYCOLLECTION.
 *
 */
public class ForceCollection extends FunctionBase1 {

	@Override
	public NodeValue exec(NodeValue arg0) {
        try {
            GeometryWrapper geom = GeometryWrapper.extract(arg0);
            Geometry geometry = geom.getXYGeometry();
            WKTReader reader=new WKTReader();
            Geometry geomet=reader.read("GEOMETRYCOLLECTION("+geometry.toText()+")");
            GeometryWrapper nthGeom = GeometryWrapperFactory.createGeometry(geomet, geom.getSrsURI(), geom.getGeometryDatatypeURI());

            return nthGeom.asNodeValue();
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        } catch (ParseException e) {
        	throw new ExprEvalException(e.getMessage(), e);
		}
	}

}
