package de.hsmainz.cs.semgis.arqextension.geometry.constructor;

import java.io.IOException;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.jdom2.JDOMException;
import org.locationtech.jts.geom.Geometry;

import io.github.galbiston.geosparql_jena.implementation.DimensionInfo;
import io.github.galbiston.geosparql_jena.implementation.GeometryWrapper;
import io.github.galbiston.geosparql_jena.implementation.parsers.gml.GMLReader;
import io.github.galbiston.geosparql_jena.implementation.vocabulary.Geo;

/**
 * Takes as input GML representation of geometry and outputs a PostGIS geometry object
 *
 */
public class GeomFromGML extends FunctionBase1{
	
	@Override
	public NodeValue exec(NodeValue geometryLiteral) {
        try {
        	GMLReader gmlReader = GMLReader.extract(geometryLiteral.getString());
            Geometry geometry = gmlReader.getGeometry();
            DimensionInfo dimensionInfo = gmlReader.getDimensionInfo();

            return new GeometryWrapper(geometry, gmlReader.getSrsURI(), Geo.GML).asNodeValue();           
        } catch (DatatypeFormatException ex) {
            throw new ExprEvalException(ex.getMessage(), ex);
        } catch (JDOMException e) {
        	throw new ExprEvalException(e.getMessage(), e);
		} catch (IOException e) {
			throw new ExprEvalException(e.getMessage(), e);
		}
	}
}
